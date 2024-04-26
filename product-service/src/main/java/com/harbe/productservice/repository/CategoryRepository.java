package com.harbe.productservice.repository;

import com.harbe.productservice.dto.model.CategoryDto;
import com.harbe.productservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByUrlKey(String urlKey);
}
