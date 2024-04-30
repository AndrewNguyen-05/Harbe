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
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    double total;
    String status;
    String paymentMethod;
    String paymentStatus;
    double shippingFee;
    String note;
    LocalDateTime createdAt;
    Address address;

}
