package com.harbe.productservice.repository;

import com.harbe.productservice.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByUrlKey(String urlKey);

    @Query(value = "SELECT * FROM Categories c WHERE " +
            "c.name LIKE CONCAT('%', :name, '%')", nativeQuery = true
    )
    Page<Category> searchCategoriesByName(@Param("name") String name, Pageable pageable);
}
