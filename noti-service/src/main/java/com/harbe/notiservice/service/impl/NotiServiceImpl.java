package com.harbe.notiservice.service.impl;

import com.harbe.notiservice.dto.model.NotiDto;
import com.harbe.notiservice.dto.response.ProductDto;
import com.harbe.notiservice.entity.Notification;
import com.harbe.notiservice.repository.NotiRepository;
import com.harbe.notiservice.service.NotiService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.Instant;

@Service
@AllArgsConstructor
public class NotiServiceImpl implements NotiService {
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
        notiDto.setTimestamp(Instant.now());
        return notiDto;
    }

    public NotiDto getDefaultProduct(long productId, Exception exception) {
        NotiDto notiDto = new NotiDto();
        notiDto.setMessage("Thông báo này đang được sữa chữa!. Vui lòng thử lại sau.");
        notiDto.setTitle("Thông báo sản phẩm");
        notiDto.setTimestamp(Instant.now());

        return notiDto;
    }

}
