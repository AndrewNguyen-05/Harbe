package com.harbe.cartservice.service.impl;

import com.harbe.cartservice.dto.request.CartItemRequest;
import com.harbe.cartservice.dto.request.ProductCartDeletionRequest;
import com.harbe.cartservice.dto.request.UpdateCartRequest;
import com.harbe.cartservice.dto.model.ProductDto;
import com.harbe.cartservice.service.CartRedisService;
import com.harbe.cartservice.service.base.impl.BaseRedisServiceImpl;
import com.harbe.cartservice.exception.ResourceNotFoundException;
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
    public void addProductToCart(String userId, CartItemRequest item) {
        String key = "cart:user-" + userId;

        // Tao 1 stringbuilder de noi chuoi voi hieu nang tot hon la cong string
        StringBuilder fieldKeyBuilder;

        int updateQuantity;

        // Kiem tra xem product co option ko
        if(Objects.nonNull(item.getProductItemId())){

            fieldKeyBuilder = new StringBuilder("product_item:");

            // Bien boolean de chi ra phan tu dau tien, khong can them dau "," phia truoc
            // Ham for dung de ghep cac optionId lai thanh 1 chuoi co dang "5,10,12"
            boolean isFirst = true;
            for (Long optionId : item.getProductItemId()) {
                if (!isFirst) {
                    fieldKeyBuilder.append(",");
                } else {
                    isFirst = false;
                }
                fieldKeyBuilder.append(optionId);
            }

        } else {
            fieldKeyBuilder = new StringBuilder("product:");
            fieldKeyBuilder.append(item.getProductId());
        }

        // Ep kieu ve lai String de thao tac
        String fieldKey = fieldKeyBuilder.toString();

        if (this.hashExist(userId, fieldKey)) {
            updateQuantity = (Integer) this.hashGet(userId, fieldKey) + item.getQuantity();
        } else {
            updateQuantity = item.getQuantity();
        }

        this.hashSet(key, fieldKey, updateQuantity);
    }

    @Override
    public void updateProductInCart(String userId, UpdateCartRequest item) {
        String key = "cart:user-" + userId;
        String fieldKey;
        long delta = item.getDelta();

        if(Objects.nonNull(item.getProductItemId())){
            // Tao 1 stringBuilder de noi chuoi
            StringBuilder fieldKeyBuilder = new StringBuilder("product_item:");

            // logic xu ly tuong tu voi vong for trong ham addToCart
            boolean isFirst = true;
            for(Long optionId : item.getProductItemId()){
                if (!isFirst) {
                    fieldKeyBuilder.append(",");
                } else {
                    isFirst = false;
                }
                fieldKeyBuilder.append(optionId);
            }

            fieldKey = fieldKeyBuilder.toString();

        } else {
            fieldKey = "product:" + item.getProductId();
        }

        Long quantityLeft = this.hashIncrBy(key, fieldKey, delta);

        if(quantityLeft <= 0){
            this.delete(key, fieldKey);
        }
    }

    @Override
    public void deleteProductInCart(String userId, ProductCartDeletionRequest request) {
        if(Objects.nonNull(request.getProductItemId())){
            // Tao 1 stringBuilder de noi chuoi
            StringBuilder fieldKeyBuilder = new StringBuilder("product_item:");

            // logic xu ly tuong tu voi vong for trong ham addToCart
            boolean isFirst = true;
            for(Long optionId : request.getProductItemId()){
                if (!isFirst) {
                    fieldKeyBuilder.append(",");
                } else {
                    isFirst = false;
                }
                fieldKeyBuilder.append(optionId);
            }

            this.checkFieldKeyExist("cart:user-" + userId, fieldKeyBuilder.toString());
            this.delete("cart:user-" + userId,  fieldKeyBuilder.toString());
        } else {
            this.checkFieldKeyExist("cart:user-" + userId, "product:" + request.getProductId());
            this.delete("cart:user-" + userId, "product:" + request.getProductId());
        }
    }

    @Override
    public void deleteAllProductsInCart(String userId){
        this.delete("cart:user-" + userId);
    }

    @Override
    public List<ProductDto> getProductsFromCart(String userId) {
        String key = "cart:user-" + userId;
        Map<String, Object> products = this.getField(key);

        List<ProductDto> productList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : products.entrySet()) {
            // Tao 1 bien co hieu de danh dau xem id nay la cua product hay product_item
            boolean isProductItem;

            //Dau tien la lay key ra roi sau do split bang dau ":"
            //vi dinh dang du lieu se la product:1 hoac product_item:1 (dua tren san pham do co option hay ko)
            String[] arrKey = entry.getKey().split(":");

            //Neu la product thi bien co hieu la false, nguoc lai la true
            isProductItem = arrKey[0].equals("product_item");

            ProductDto productDto = getProductById(arrKey[1], isProductItem); // Gọi đến URL để lấy thông tin sản phẩm dua tren id

            if (productDto != null) {
                //Su dung lenh hget cart:user-1 product:1 de lay len so luong sp trong gio hang
                int quantity = (int)this.hashGet(key, entry.getKey());
                productDto.setQuantity(quantity);
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

            // Web service la POST vi phai gui body request, body request o day la 1 chuoi cac productItemId
            // co dang "5,10,12"
            productDto = this.webClient.post()
                    .uri("http://localhost:8081/api/v1/products/product-options")
                    .bodyValue(id)
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


    private void checkFieldKeyExist(String key, String keyField){
        if(!this.hashExist(key, keyField)){
            throw new ResourceNotFoundException(key, keyField, 0);
        }
    }

}
