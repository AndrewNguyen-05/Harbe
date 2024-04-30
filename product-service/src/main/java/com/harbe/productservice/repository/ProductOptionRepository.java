package com.harbe.productservice.repository;

import com.harbe.productservice.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
    List<ProductOption> findByProductId(Long productId);
    @Query("SELECT o FROM ProductOption o WHERE o.product.id = :productId AND o.name = :name")
    List<ProductOption> findByProductIdAndName(@Param("productId") Long productId, @Param("name") String name);

}
