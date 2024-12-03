package com.harbe.productservice.repository;

import com.harbe.productservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM Products p WHERE " +
            "p.name LIKE CONCAT('%', :name, '%')", nativeQuery = true
    )
    Page<Product> searchProductByName(@Param("name") String name, Pageable pageable);
}
