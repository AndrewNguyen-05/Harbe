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
            // Tao 1 bien co hieu de danh dau xem id nay la cua product hay product_item
            boolean isProductItem = false;

            //Dau tien la lay key ra roi sau do split bang dau ":"
            //vi dinh dang du lieu se la product:1 hoac product_item:1 (dua tren san pham do co option hay ko)
            String[] arrKey = entry.getKey().split(":");

            //Neu la product thi bien co hieu la false, nguoc lai la true
            isProductItem = arrKey[0].equals("product_item");

            ProductDto productDto = getProductById(arrKey[1], isProductItem); // Gọi đến URL để lấy thông tin sản phẩm dua tren id
            if (productDto != null) {
                productList.add(productDto);
            }
        }
        return productList;
    }

    private ProductDto getProductById(String id, boolean isProductItem) {
        // Kiem tra, neu la product_item thi goi toi duong dan lay product dua tren optionId,
        // nguoc lai se la goi product theo id nhu bth
        ProductDto productDto;
        if(isProductItem){
            productDto = this.webClient.get()
                    .uri("http://localhost:8081/api/v1/products/product-options/" + id)
                    .retrieve()
                    .bodyToMono(ProductDto.class)
                    .block();
        } else {
            productDto = this.webClient.get()
                    .uri("http://localhost:8081/api/v1/products/" + id)
                    .retrieve()
                    .bodyToMono(ProductDto.class)
                    .block();
        }

        return productDto;
    }


}
