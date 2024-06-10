package com.harbe.notiservice.service.impl;

import com.harbe.notiservice.dto.mapper.NotiMapper;
import com.harbe.notiservice.dto.model.NotiDto;
import com.harbe.notiservice.dto.response.NotiResponseDto;
import com.harbe.notiservice.dto.response.ObjectResponse;
import com.harbe.notiservice.dto.response.ProductDto;
import com.harbe.notiservice.entity.Notification;
import com.harbe.notiservice.exception.ResourceNotFoundException;
import com.harbe.notiservice.repository.NotiRepository;
import com.harbe.notiservice.service.NotiService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NotiServiceImpl implements NotiService {
    private final NotiMapper notiMapper;
    private NotiRepository notiRepository;
    private WebClient webClient;

    //@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultProduct")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultProduct")
    @Override
    public NotiDto getNotiWithProduct(long productId) {
        ProductDto productDto = new ProductDto();
        productDto = this.webClient.get()
                .uri("http://localhost:8081/api/v1/products/" + productId)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();

        NotiDto notiDto = new NotiDto();
        String productName = productDto.getName();
        double discount = productDto.getDiscountRate();
        double price = productDto.getPrice();

        notiDto.setMessage("Sản phẩm " + productName + " đang có khuyến mãi sốc lên đến " + discount + ", với giá gốc là " + price +"!!!! Hãy nhanh chóng mua sắm ngay hôm nay!");
        notiDto.setTitle("KHUYẾN MÃI CỰC SỐC CHÀO HÈ 2024!");
        notiDto.setTimestamp(Date.from(Instant.now()));
        return notiDto;
    }

    @Override
    public ObjectResponse<NotiDto> getAllNotifications(int pageNo, int pageSize, String sortBy, String sortDir) {
        // Tao sort
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // Tao 1 pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        // Tao 1 mang cac trang product su dung find all voi tham so la pageable
        Page<Notification> pages = this.notiRepository.findAll(pageable);

        // Lay ra gia tri (content) cua page
        List<Notification> notifications = pages.getContent();


        // Ep kieu sang dto
        List<NotiDto> content = notifications.stream().map(notification -> notiMapper.mapToDto(notification)).collect(Collectors.toList());

        // Gan gia tri (content) cua page vao ProductResponse de tra ve
        ObjectResponse<NotiDto> response = new ObjectResponse();
        response.setContent(content);
        response.setTotalElements(pages.getTotalElements());
        response.setPageNo(pages.getNumber());
        response.setPageSize(pages.getSize());
        response.setLast(pages.isLast());

        return response;
    }

    @Override
    public NotiDto getNotificationById(long id) {
        Notification notification = this.notiRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Notification", "id", id));
        NotiDto notiDto = this.notiMapper.mapToDto(notification);
        return notiDto;
    }

    @Override
    public NotiDto createNotification(NotiResponseDto notiDto) {
        Notification notification = this.notiMapper.mapToResponseEntity(notiDto);
        notification.setTimestamp(Date.from(Instant.now()));
        this.notiRepository.save(notification);

        return this.notiMapper.mapToDto(notification);
    }

    @Override
    public NotiDto updateNotification(long notiId, NotiResponseDto notiDto) {
        Notification notification = this.notiRepository.findById(notiId).orElseThrow(() -> new ResourceNotFoundException("Notification", "id", notiId));

        notification.setTitle(notiDto.getTitle());
        notification.setTimestamp(Date.from(Instant.now()));
        notification.setMessage(notiDto.getMessage());
        this.notiRepository.save(notification);

        return this.notiMapper.mapToDto(notification);
    }

    @Override
    public void deleteNotification(long id) {
        Notification notification = this.notiRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Notification", "id", id));        this.notiRepository.delete(notification);
    }

    public NotiDto getDefaultProduct(long productId, Exception exception) {
        NotiDto notiDto = new NotiDto();
        notiDto.setMessage("Thông báo này đang được sữa chữa!. Vui lòng thử lại sau.");
        notiDto.setTitle("Thông báo sản phẩm");
        notiDto.setTimestamp(Date.from(Instant.now()));

        return notiDto;
    }

}
