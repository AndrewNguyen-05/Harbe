package com.harbe.productservice.repository;

import com.harbe.productservice.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
    List<ProductOption> findByProductId(Long productId);
    //ProductOption findByNameAndProductId(String name, Long productId);
}
