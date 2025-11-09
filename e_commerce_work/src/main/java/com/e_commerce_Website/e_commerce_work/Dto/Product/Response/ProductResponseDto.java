package com.e_commerce_Website.e_commerce_work.Dto.Product.Response;

import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.e_commerce_Website.e_commerce_work.Model.Product;
import com.e_commerce_Website.e_commerce_work.Dto.Category.Response.CategoryResponseDto;
import com.e_commerce_Website.e_commerce_work.Dto.Brand.Response.BrandResponseDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String description;

    private String sku;

    private BigDecimal price;

    private BigDecimal discountedPrice;

    private String currency;

    private Boolean isAvailable;

    private Integer stockQuantity;

    private String slug; // SEO-friendly URL

    private List<String> tags; // Product tags

    private Double averageRating; // Average review rating

    private Integer reviewCount; // Total number of reviews

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String createdBy;

    private String updatedBy;

    private CategoryResponseDto category;

    private BrandResponseDto brand;

    public ProductResponseDto(Product product) {
        if (product != null) {
            this.id = product.getId();
            this.name = product.getName();
            this.description = product.getDescription();
            this.sku = product.getSku();
            this.price = product.getPrice();
            this.discountedPrice = product.getDiscountedPrice();
            this.currency = product.getCurrency();
            this.isAvailable = product.getIsAvailable();
            this.stockQuantity = product.getStockQuantity();
            this.slug = product.getSlug();
            this.category = product.getCategory() != null ? new CategoryResponseDto(product.getCategory()) : null;
            this.brand = product.getBrand() != null ? new BrandResponseDto(product.getBrand()) : null;
            this.tags = product.getTags();
            this.averageRating = product.getAverageRating();
            this.reviewCount = product.getReviewCount();
            this.createdAt = product.getCreatedAt();
            this.updatedAt = product.getUpdatedAt();
            this.createdBy = product.getCreatedBy();
            this.updatedBy = product.getUpdatedBy();
        }
    }
}
