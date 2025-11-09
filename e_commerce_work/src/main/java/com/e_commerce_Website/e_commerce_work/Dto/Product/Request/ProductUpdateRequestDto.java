package com.e_commerce_Website.e_commerce_work.Dto.Product.Request;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductUpdateRequestDto {

    @NotNull(message = "Product ID is required for update")
    private Long id;

    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 2, max = 150, message = "Product name must be between 2 and 150 characters")
    private String name;

    @NotBlank(message = "Product description cannot be blank")
    @Size(min = 10, max = 5000, message = "Description must be between 10 and 5000 characters")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    @Digits(integer = 10, fraction = 2, message = "Invalid price format")
    private BigDecimal price;

    @DecimalMin(value = "0.0", message = "Discount price cannot be negative")
    @Digits(integer = 10, fraction = 2, message = "Invalid discount price format")
    private BigDecimal discountPrice;

    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock quantity cannot be negative")
    private Integer stockQuantity;

    @NotNull(message = "Currency is required")
    @Size(min = 3, max = 3, message = "Currency must be 3 characters")
    private String currency;

    @NotNull(message = "Is available is required")
    private Boolean isAvailable;    

    @NotNull(message = "Slug is required")
    @Size(min = 2, max = 255, message = "Slug must be between 2 and 255 characters")
    private String slug;

    @NotNull(message = "SKU is required")
    @Size(min = 2, max = 50, message = "SKU must be between 2 and 50 characters")
    private  String sku;

    @NotNull(message = "Is active is required")
    private Boolean isActive;
    

    @NotNull(message = "Tags are required")
    @Size(min = 1, max = 10, message = "Tags must be between 1 and 10")
    private List<String> tags;


    @NotNull(message = "Average rating is required")
    @DecimalMin(value = "0.0", message = "Average rating cannot be negative")
    @DecimalMax(value = "5.0", message = "Average rating cannot exceed 5")
    private Double averageRating;

    @NotNull(message = "Review count is required")
    @Min(value = 0, message = "Review count cannot be negative")
    private Integer reviewCount;
}
