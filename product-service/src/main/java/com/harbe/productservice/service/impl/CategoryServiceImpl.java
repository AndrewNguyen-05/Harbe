package com.harbe.productservice.service.impl;

import com.harbe.productservice.dto.message.CategoryResponseDto;
import com.harbe.productservice.exception.HarbeAPIException;
import com.harbe.productservice.exception.ResourceNotFoundException;
import com.harbe.productservice.dto.mapper.CategoryMapper;
import com.harbe.productservice.dto.mapper.CreateCategoryMapper;
import com.harbe.productservice.dto.model.CategoryDto;
import com.harbe.productservice.dto.model.CreateCategoryDto;
import com.harbe.productservice.entity.Category;
import com.harbe.productservice.repository.CategoryRepository;
import com.harbe.productservice.service.CategoryService;
import com.harbe.productservice.utils.SlugConvert;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    private CreateCategoryMapper createCategoryMapper;
    private CategoryMapper categoryMapper;
    @Override
    public CategoryDto createCategory(CreateCategoryDto categoryDto) {
        Category newCategory = this.createCategoryMapper.mapToEntity(categoryDto);

        newCategory.setUrlKey(SlugConvert.convert(categoryDto.getName()));

        Category responseCategory = this.categoryRepository.save(newCategory);

        return this.categoryMapper.mapToDto(responseCategory);
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        return this.categoryMapper.mapToDto(category);
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        List<Category> categories = this.categoryRepository.findAll();

        return categories.stream().map(category -> this.categoryMapper.mapToResponseDto(category)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(CreateCategoryDto categoryDto, Long id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        category.setName(categoryDto.getName());
        category.setThumbnailUrl(categoryDto.getThumbnailUrl());
        //category.setPrimary(categoryDto.isPrimary());
        category.setParentId(categoryDto.getParentId());
        category.setUrlKey(SlugConvert.convert(categoryDto.getName()));

        Category responseCategory = this.categoryRepository.save(category);

        return this.categoryMapper.mapToDto(responseCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        this.categoryRepository.delete(category);
    }
}
