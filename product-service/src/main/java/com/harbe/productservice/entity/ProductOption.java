package com.harbe.productservice.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "product_options")
public class ProductOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = false)
    private String name;
    private String value;
//    @ElementCollection
//    private List<String> value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Ghi de 2 ham equals va hashcode de dam bao tinh dung dan cho ham contains
    // để so sánh name (kiểm tra xem có tồn tại không dựa vào name)
    @Override
    public boolean equals(Object o) {
        // kiem tra tham chieu
        if (this == o) return true;

        // Kiem tra xem doi tuong duoc so sanh co rong ko ? hoặc có phải là instance của 1 lớp khác không
        if (o == null || this.getClass() != o.getClass()) return false;

        // ep kieu ve option
        ProductOption that = (ProductOption) o;

        return Objects.equals(this.name, that.name);
    }

    // Hàm hashcode trả về giá trị hash được tính toán dựa trên name
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
