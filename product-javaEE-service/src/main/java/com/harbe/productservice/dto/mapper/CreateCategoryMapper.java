package com.harbe.productservice.dto.mapper;

import com.harbe.productservice.dto.model.CreateCategoryDto;
import com.harbe.productservice.entity.Category;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateCategoryMapper {
    private ModelMapper mapper;
    public CreateCategoryDto mapToDto(Category category){
        CreateCategoryDto categoryDto = mapper.map(category, CreateCategoryDto.class);
        return categoryDto;
    }

    public Category mapToEntity(CreateCategoryDto createCategoryDto){
        Category category = mapper.map(createCategoryDto, Category.class);
        return category;
    }
}
