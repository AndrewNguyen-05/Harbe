package com.harbe.orderservice.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "\"order\"")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double total;
    private String status; //PREPARING, SHIPPING, COMPLETED, CANCELED
    private String paymentMethod; //PAYPAL, COD
    private String paymentStatus; //PENDING, COMPLETED, CANCELED
    private double shippingFee;
    private String note;
    private LocalDateTime createdAt;
    private long addressId;
    private long userId;
}
