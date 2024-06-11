package com.harbe.productservice.repository;

import com.harbe.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM Products p WHERE " +
            "p.name LIKE CONCAT('%', :name, '%')", nativeQuery = true
    )
    List<Product> searchProductByName(@Param("name") String name);
}
