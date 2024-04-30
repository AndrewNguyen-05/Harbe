package com.harbe.cartservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@RedisHash("cart")
public class Cart implements Serializable {

    @Id
    private Long id;

    private Long userId;


    private Long productItemId;
    private Long productId;
    private Long quantity;
}
