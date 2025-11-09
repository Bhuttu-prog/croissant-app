package com.e_commerce_Website.e_commerce_work.Dto.Category.Request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequestDto {

    @NotBlank(message = "Category name cannot be blank")
    @Size(min = 2, max = 100, message = "Category name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Category description cannot be blank")
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String description;

    @Size(max = 255, message = "Slug must be less than 255 characters")
    private String slug; // SEO-friendly URL (optional)

    @Size(max = 500, message = "Image URL must be less than 500 characters")
    private String imageUrl;

    @Builder.Default
    private Boolean isActive = true; // Default active

   
}
