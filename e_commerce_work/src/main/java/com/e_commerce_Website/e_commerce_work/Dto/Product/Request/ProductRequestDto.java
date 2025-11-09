package com.e_commerce_Website.e_commerce_work.Dto.Product.Request;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

import com.e_commerce_Website.e_commerce_work.Dto.Brand.Request.BrandRequestDto;
import com.e_commerce_Website.e_commerce_work.Dto.Category.Request.CategoryRequestDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDto {

    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 2, max = 150, message = "Product name must be between 2 and 150 characters")
    private String name;

    @NotBlank(message = "Product description cannot be blank")
    @Size(min = 10, max = 5000, message = "Description must be between 10 and 5000 characters")
    private String description;

    @NotBlank(message = "SKU is required")
    @Size(min = 2, max = 50, message = "SKU must be between 2 and 50 characters")
    private String sku;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    @Digits(integer = 10, fraction = 2, message = "Invalid price format")
    private BigDecimal price;

    @DecimalMin(value = "0.0", message = "Discount price cannot be negative")
    @Digits(integer = 10, fraction = 2, message = "Invalid discount price format")
    private BigDecimal discountedPrice;

    @Size(max = 3, message = "Currency code must be 3 characters")
    @Builder.Default
    private String currency = "USD";

    @NotNull(message = "Tags are required")
    @Size(min = 1, max = 10, message = "Tags must be between 1 and 10")
    private List<String> tags;

    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock quantity cannot be negative")
    private Integer stockQuantity;

    @Builder.Default
    private Boolean isAvailable = true;

    @Size(max = 255, message = "Slug must be less than 255 characters")
    private String slug; // Optional: SEO-friendly URL

    @NotNull(message = "Category is required")
    private CategoryRequestDto category;

    @NotNull(message = "Brand is required")
    private BrandRequestDto brand;
}
