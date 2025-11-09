package com.e_commerce_Website.e_commerce_work.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "products" , 
    uniqueConstraints = {
        @UniqueConstraint(name = "sku_unique", columnNames = "sku"),
        @UniqueConstraint(name = "slug_unique", columnNames = "slug")
    },
    indexes = {
        @Index(name = "idx_product_name", columnList = "name"),
        @Index(name = "idx_product_sku", columnList = "sku"),
        @Index(name = "idx_product_slug", columnList = "slug"),
        @Index(name = "idx_product_category_id", columnList = "category_id"),
        @Index(name = "idx_product_brand_id", columnList = "brand_id"),
        @Index(name = "idx_product_price", columnList = "price"),
    }
)
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String sku;

    @Column(nullable = false)
    private String name;

    @Lob
    private String description;

    private String shortDescription;

    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    @Column(precision = 10, scale = 2)
    @DecimalMin(value = "0.0", inclusive = true, message = "Discounted price must be >= 0")
    private BigDecimal discountedPrice;

    private String currency = "USD";

    private Integer stockQuantity;
    private Integer lowStockThreshold = 5;
    private Boolean isAvailable = true;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductImage> productImages;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductReview> productReviews;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<CartItem> cartItems;

    @Column(unique = true)
    private String slug;

    @ElementCollection
    private List<String> tags;

    @DecimalMin(value = "0.0", message = "Rating cannot be negative")
    @DecimalMax(value = "5.0", message = "Rating cannot exceed 5")
    private Double averageRating = 0.0;
    
    @Min(value = 0, message = "Review count cannot be negative")
    private Integer reviewCount = 0;

    @NotNull(message = "Active status is required")
    private Boolean isActive = true;


    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    private String createdBy;


    private String updatedBy;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.createdBy = "System";
        this.updatedBy = "System";
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = "System";
    }

    @PreRemove
    public void preRemove() {
        // Soft delete tracking if needed
    }
}
