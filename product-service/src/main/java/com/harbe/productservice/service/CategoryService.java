package com.harbe.productservice.service;

import com.harbe.productservice.dto.model.CategoryDto;
import com.harbe.productservice.dto.model.CreateCategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CreateCategoryDto categoryDto);
    CategoryDto getCategoryById(Long id);
    List<CategoryDto> getAllCategories();
    CategoryDto updateCategory(CreateCategoryDto categoryDto, Long id);
    void deleteCategory(Long id);
}