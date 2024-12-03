package com.harbe.productservice.dto.mapper;

import com.harbe.productservice.dto.model.ProductOptionDto;
import com.harbe.productservice.entity.ProductOption;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OptionMapper {
    private ModelMapper mapper;
    public ProductOptionDto mapToDto(ProductOption option){
        ProductOptionDto optionDto = mapper.map(option, ProductOptionDto.class);
        return optionDto;
    }

    public ProductOption mapToEntity(ProductOptionDto optionDto){
        ProductOption option = mapper.map(optionDto, ProductOption.class);
        return option;
    }
}
