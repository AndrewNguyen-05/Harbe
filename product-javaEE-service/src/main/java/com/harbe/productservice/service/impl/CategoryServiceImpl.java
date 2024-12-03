package com.harbe.productservice.service.impl;

import com.harbe.productservice.dto.mapper.CategoryMapper;
import com.harbe.productservice.dto.mapper.CreateCategoryMapper;
import com.harbe.productservice.dto.message.CategoryResponseDto;
import com.harbe.productservice.dto.model.CategoryDto;
import com.harbe.productservice.dto.model.CreateCategoryDto;
import com.harbe.productservice.dto.response.ObjectResponse;
import com.harbe.productservice.entity.Category;
import com.harbe.productservice.exception.ResourceNotFoundException;
import com.harbe.productservice.repository.CategoryRepository;
import com.harbe.productservice.service.CategoryService;
import com.harbe.productservice.utils.SlugConvert;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        CategoryDto categoryDto = this.categoryMapper.mapToDto(category);
        return categoryDto;
    }

    @Override
    public ObjectResponse<CategoryResponseDto> getAllCategories(int pageNo, int pageSize, String sortBy, String sortDir) {
        // Tao sort
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // Tao 1 pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        // Tao 1 mang cac trang product su dung find all voi tham so la pageable
        Page<Category> pages = this.categoryRepository.findAll(pageable);

        // Lay ra gia tri (content) cua page
        List<Category> categories = pages.getContent();


        // Ep kieu sang dto
        List<CategoryResponseDto> content = categories.stream().map(category -> categoryMapper.mapToResponseDto(category)).collect(Collectors.toList());

        // Gan gia tri (content) cua page vao ProductResponse de tra ve
        ObjectResponse<CategoryResponseDto> response = new ObjectResponse();
        response.setContent(content);
        response.setTotalElements(pages.getTotalElements());
        response.setPageNo(pages.getNumber());
        response.setPageSize(pages.getSize());
        response.setLast(pages.isLast());
        response.setTotalPages(pages.getTotalPages());

        return response;
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

    @Override
    public ObjectResponse<CategoryResponseDto> searchCategory(String name, int pageNo, int pageSize, String sortBy, String sortDir){
        // Tao sort
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // Tao 1 pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        // Tao 1 mang cac trang product su dung find all voi tham so la pageable
        Page<Category> pages = this.categoryRepository.searchCategoriesByName(name, pageable);

        // Lay ra gia tri (content) cua page
        List<Category> categories = pages.getContent();


        // Ep kieu sang dto
        List<CategoryResponseDto> content = categories.stream().map(category -> categoryMapper.mapToResponseDto(category)).collect(Collectors.toList());

        // Gan gia tri (content) cua page vao ProductResponse de tra ve
        ObjectResponse<CategoryResponseDto> response = new ObjectResponse();
        response.setContent(content);
        response.setTotalElements(pages.getTotalElements());
        response.setPageNo(pages.getNumber());
        response.setPageSize(pages.getSize());
        response.setLast(pages.isLast());
        response.setTotalPages(pages.getTotalPages());

        return response;
    }
}
