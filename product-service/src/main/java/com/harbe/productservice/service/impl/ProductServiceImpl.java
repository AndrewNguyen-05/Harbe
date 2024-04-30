package com.harbe.productservice.service.impl;

import com.harbe.commons.response.ObjectResponse;
import com.harbe.productservice.dto.mapper.OptionMapper;
import com.harbe.productservice.dto.mapper.ProductMapper;
import com.harbe.productservice.dto.mapper.SpecificationMapper;
import com.harbe.productservice.dto.message.ProductResponse;
import com.harbe.productservice.dto.model.ProductDto;
import com.harbe.productservice.entity.Category;
import com.harbe.productservice.entity.Product;
import com.harbe.productservice.entity.ProductOption;
import com.harbe.productservice.entity.ProductSpecification;
import com.harbe.productservice.exception.ResourceNotFoundException;
import com.harbe.productservice.repository.CategoryRepository;
import com.harbe.productservice.repository.ProductOptionRepository;
import com.harbe.productservice.repository.ProductRepository;
import com.harbe.productservice.repository.ProductSpecificationRepository;
import com.harbe.productservice.service.ProductService;
import com.harbe.productservice.utils.SlugConvert;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    //Repositories
    private ProductRepository productRepository;
    private ProductOptionRepository optionRepository;
    private ProductSpecificationRepository specificationRepository;
    private CategoryRepository categoryRepository;

    //Mappers
    private ProductMapper productMapper;
    private OptionMapper optionMapper;
    private SpecificationMapper specificationMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product newProduct = productMapper.mapToEntity(productDto);

        // Kiểm tra xem có category slug chưa, nếu chưa thì bỏ qua (uncategorized)
        //, nếu có rồi thì tự động convert sang slug (nếu là slug sẵn thì k thay đổi)
        // Sau đó sẽ gọi hàm và tìm dưới db và gán cho product
        if(!newProduct.getCategoryUrl().equals("")){
            String categoryUrl = SlugConvert.convert(newProduct.getCategoryUrl());
            Category category = this.categoryRepository.findByUrlKey(categoryUrl);
            newProduct.setCategory(category);
        }

        // Convert tu dong ten sp sang slug, khong yeu cau phai co slug trong request body
        String productSlug = SlugConvert.convert(productDto.getName());
        newProduct.setProductSlug(productSlug);

        // Gán các option vào product
        List<ProductOption> options = newProduct.getOptions();
        for(ProductOption option : options){
            newProduct.addOption(option);
        }

        // Gán các specification vào product
        Set<ProductSpecification> specifications = newProduct.getSpecifications();
        for(ProductSpecification specification : specifications){
            newProduct.addSpecification(specification);
        }

        Product productResponse = this.productRepository.save(newProduct);

        return productMapper.mapToDto(productResponse);
    }

    @Override
    public ObjectResponse<ProductDto> getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir) {
        // Tao sort
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // Tao 1 pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        // Tao 1 mang cac trang product su dung find all voi tham so la pageable
        Page<Product> pages = this.productRepository.findAll(pageable);

        // Lay ra gia tri (content) cua page
        List<Product> products = pages.getContent();

        for(Product product : products){

        }

        // Ep kieu sang dto
        List<ProductDto> content = products.stream().map(product -> productMapper.mapToDto(product)).collect(Collectors.toList());

        // Gan gia tri (content) cua page vao ProductResponse de tra ve
        ObjectResponse<ProductDto> response = new ObjectResponse();
        response.setContent(content);
        response.setTotalElements(pages.getTotalElements());
        response.setPageNo(pages.getNumber());
        response.setPageSize(pages.getSize());
        response.setLast(pages.isLast());

        return response;
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        ProductDto productDto = productMapper.mapToDto(product);

        return productDto;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Long productId) {
        Product product = this.productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setDescription(productDto.getDescription());
        product.setDiscountRate(productDto.getDiscountRate());
        product.setPrice(productDto.getPrice());
        product.setQuantitySold(productDto.getQuantitySold());
        product.setReviewCount(productDto.getReviewCount());
        product.setRatingAverage(productDto.getRatingAverage());
        product.setThumbnailUrl(productDto.getThumbnailUrl());
        product.setCategoryUrl(productDto.getCategoryUrl());
        product.setProductSlug(SlugConvert.convert(product.getName()));

        if(!product.getCategoryUrl().equals("")){
            String categoryUrl = SlugConvert.convert(product.getCategoryUrl());
            Category category = this.categoryRepository.findByUrlKey(categoryUrl);
            product.setCategory(category);
        }


        // Xu ly logic cho update option
        //1. Lay het tat ca option tu product update
        Set<ProductOption> options = productDto.getOptions().stream().map(option -> optionMapper.mapToEntity(option)).collect(Collectors.toSet());

        //2. Lay het tat ca option tu product hien tai (Su dung cach copy de khong tham chieu toi Set goc)
        Set<ProductOption> productOptions = new HashSet<>(product.getOptions());

        //3. Duyet qua tat ca option cua product hien tai,
        // neu ko nam trong Set option cua product update thi se bi xoa di
        for(ProductOption option : productOptions){
            boolean isContain = options.contains(option);

            // Ham dismissOption su dung de ngat bo lien ket (xoa khoi product truoc)
            // Can phai ngat lien ket voi Product truoc (do bidirectional relationship), sau do moi
            // xoa trong database
            if(!isContain && product.dismissOption(option)){
                ProductOption deletedOption = this.optionRepository.findById(option.getId()).orElseThrow(() -> new ResourceNotFoundException("Option", "id", option.getId()));
                this.optionRepository.delete(deletedOption);
            }
        }

        //3. Cuoi cung la thuc hien logic update (Neu chua co thi se dc them,
        // con neu da co (cung name) thi se dc thay doi value)
        for(ProductOption option : options){
            product.updateOption(option);
        }

        // Xy ly tuong tu cho product specification
        Set<ProductSpecification> specifications = productDto.getSpecifications().stream().map(specification -> specificationMapper.mapToEntity(specification)).collect(Collectors.toSet());
        Set<ProductSpecification> productSpecifications = new HashSet<>(product.getSpecifications());
        for(ProductSpecification specification : productSpecifications){
            boolean isContain = specifications.contains(specification);

            // Ham dismissSpecification su dung de ngat bo lien ket (xoa khoi product truoc)
            if(!isContain && product.dismissSpecification(specification)){
                ProductSpecification deletedSpecification = this.specificationRepository.findById(specification.getId()).orElseThrow(() -> new ResourceNotFoundException("Specification", "id", specification.getId()));
                this.specificationRepository.delete(deletedSpecification);
            }
        }

        for(ProductSpecification specification : specifications){
            product.updateSpecification(specification);
        }

        this.productRepository.save(product);

        return productMapper.mapToDto(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = this.productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        this.productRepository.delete(product);
    }

}
