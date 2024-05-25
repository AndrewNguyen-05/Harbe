package com.harbe.productservice.controller;

import com.harbe.productservice.dto.message.ProductDetailResponseDto;
import com.harbe.productservice.dto.message.ProductResponseDto;
import com.harbe.productservice.dto.response.ObjectResponse;
import com.harbe.productservice.utils.AppConstants;
import com.harbe.productservice.dto.model.ProductDto;
import com.harbe.productservice.dto.response.ProductWithOptionForCartDto;
import com.harbe.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Product",
        description = "REST APIs for Product"
)
@AllArgsConstructor

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductDto productDto){
            return new ResponseEntity<>(this.productService.createProduct(productDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get all product REST API",
            description = "Get all product REST API, can be used with param like pageNo, pageSize, sortBy, sortDir (asc, desc) for pagination and sort"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping
    public ResponseEntity<ObjectResponse<ProductDto>> getAllProducts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return new ResponseEntity<>(this.productService.getAllProducts(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailResponseDto> getProductById(@PathVariable(name = "productId") Long id){
        return new ResponseEntity<>(this.productService.getProductById(id), HttpStatus.OK);
    }
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(
            @RequestBody @Valid ProductDto productDto,
            @PathVariable(name = "productId") Long productId
    ){
        return new ResponseEntity<>(this.productService.updateProduct(productDto, productId), HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "productId") Long productId){
        this.productService.deleteProduct(productId);
        return new ResponseEntity<>("Product is deleted successfully!", HttpStatus.OK);
    }

    @PostMapping("/product-options")
    public ResponseEntity<ProductWithOptionForCartDto> findProductByProductOptionId(
            @RequestBody String idLists
    ){
        return new ResponseEntity<>(this.productService.getProductByProductOptionId(idLists), HttpStatus.OK);
    }


}
