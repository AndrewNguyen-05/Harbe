package com.harbe.cartservice.repository;

import com.harbe.cartservice.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<String, String> {
}
