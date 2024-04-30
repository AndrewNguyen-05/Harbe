package com.harbe.cartservice.service.impl;

import com.harbe.cartservice.dto.Request.CartItemRequest;
import com.harbe.cartservice.dto.Request.ProductCartDeletionRequest;
import com.harbe.cartservice.dto.model.ProductDto;
import com.harbe.cartservice.service.CartRedisService;
import com.harbe.cartservice.service.base.impl.BaseRedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class CartRedisServiceImpl extends BaseRedisServiceImpl implements CartRedisService {
    private final WebClient webClient;

    @Autowired
    public CartRedisServiceImpl(RedisTemplate<String, Object> redisTemplate, WebClient webClient){
        super(redisTemplate);
        this.webClient = webClient;
    }

    @Override
    public void addProductToCart(String userId, List<CartItemRequest> items) {
        String key = "cart:user-" + userId;
        String fieldKey;
        int updateQuantity;
        for(CartItemRequest item : items){

            if(Objects.nonNull(item.getProductItemId())){
                fieldKey = "product_item:" + item.getProductItemId();
            } else {
                fieldKey = "product:" + item.getProductId();
            }

            if (this.hashExist(userId, fieldKey)) {
                updateQuantity = (Integer) this.hashGet(userId, fieldKey) + item.getQuantity();
            } else {
                updateQuantity = item.getQuantity();
            }

            this.hashSet(key, fieldKey, updateQuantity);
        }
    }

    @Override
    public void updateProductInCart(String userId, List<CartItemRequest> items) {
        String key = "cart:user-" + userId;
        String fieldKey;
        Set<String> fieldKeys = new HashSet<>();
        for(CartItemRequest item : items){
            fieldKey = Objects.nonNull(item.getProductItemId()) ?
                    "product_item:" + item.getProductItemId() : "product:" + item.getProductId();
            fieldKeys.add(fieldKey);

            this.hashSet(key, fieldKey, item.getQuantity());
        }

        //this.delete(userId, fieldKeys);
    }

    @Override
    public void deleteProductInCart(String userId, ProductCartDeletionRequest request) {
        if(Objects.nonNull(request.getProductItemId())){


        }
    }

    @Override
    public List<ProductDto> getProductsFromCart(String userId) {
        Map<String, Object> products = this.getField("cart:user-" + userId);

        List<ProductDto> productList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : products.entrySet()) {
            String key = entry.getKey();
            String productId = extractProductId(key); // Tách id từ key

            ProductDto productDto = getProductById(productId); // Gọi đến URL để lấy thông tin sản phẩm
            if (productDto != null) {
                productList.add(productDto);
            }
        }
        return productList;
    }
    private String extractProductId(String key) {
        return key.substring("product:".length());
    }

    private ProductDto getProductById(String productId) {
        // Gọi đến URL sử dụng WebClient để lấy thông tin sản phẩm từ productId
        return this.webClient.get()
                .uri("http://localhost:8081/api/v1/products/" + productId)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
    }


}
