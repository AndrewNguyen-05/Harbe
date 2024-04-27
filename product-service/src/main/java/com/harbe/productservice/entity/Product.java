package com.harbe.productservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;

    @Column(length = 5000)
    private String description;
    private double price;
    private double discountRate;
    private String thumbnailUrl;
    private int reviewCount;
    private double ratingAverage;
    private int quantitySold;
    private String productSlug;
    private String categoryUrl;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductSpecification> specifications = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductOption> options = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Seller seller;

    // Cac ham xu ly logic cua product option
    public void addOption(ProductOption option){
        option.setProduct(this);
        options.add(option);
    }

    public void updateOption(ProductOption option){
        boolean flag = true;
        for(ProductOption opt : options){
            if(opt.getName().equals(option.getName())){
                opt.setValue(option.getValue());
                flag = false;
            }
        }
        if(flag) {
            addOption(option);
        }
    }

    public boolean dismissOption(ProductOption option) {
        return this.options.remove(option);
    }


    // Cac ham xu ly logic cua product specification
    public void addSpecification(ProductSpecification specification){
        specification.setProduct(this);
        specifications.add(specification);
    }

    public void updateSpecification(ProductSpecification specification){
        boolean flag = true;
        for(ProductSpecification spe : specifications){
            if(spe.getName().equals(specification.getName())){
                spe.setValue(specification.getValue());
                flag = false;
            }
        }
        if(flag) {
            addSpecification(specification);
        }
    }

    public boolean dismissSpecification(ProductSpecification specification) {
        return this.specifications.remove(specification);
    }
}
