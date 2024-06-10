package com.harbe.productservice.controller;

import com.harbe.productservice.dto.message.CategoryResponseDto;
import com.harbe.productservice.dto.model.CategoryDto;
import com.harbe.productservice.dto.model.CreateCategoryDto;
import com.harbe.productservice.dto.response.ObjectResponse;
import com.harbe.productservice.service.CategoryService;
import com.harbe.productservice.utils.AppConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Category",
        description = "REST APIs for Product"
)

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private CategoryService categoryService;
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CreateCategoryDto createCategoryDto){
        return new ResponseEntity<>(this.categoryService.createCategory(createCategoryDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ObjectResponse<CategoryResponseDto>> getAllCategories(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return new ResponseEntity<>(this.categoryService.getAllCategories(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable(name = "categoryId") Long id){
        return new ResponseEntity<>(this.categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable(name = "categoryId") Long id,
            @RequestBody CreateCategoryDto createCategoryDto
    ){
        return new ResponseEntity<>(this.categoryService.updateCategory(createCategoryDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(
            @PathVariable(name = "categoryId") Long id
    ){
        this.categoryService.deleteCategory(id);
        return new ResponseEntity<>("Delete category successfully!", HttpStatus.OK);
    }
}
