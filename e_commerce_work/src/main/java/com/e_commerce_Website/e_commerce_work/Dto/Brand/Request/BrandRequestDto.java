package com.e_commerce_Website.e_commerce_work.Dto.Brand.Request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandRequestDto {

    @NotBlank(message = "Brand name cannot be blank")
    @Size(min = 2, max = 100, message = "Brand name must be between 2 and 100 characters")
    private String name;

    @Size(max = 1000, message = "Brand description must be less than 1000 characters")
    private String description;

    @Size(max = 500, message = "Logo URL must be less than 500 characters")
    private String logoUrl; // Optional: brand logo image

    @Size(max = 255, message = "Website URL must be less than 255 characters")
    @Pattern(regexp = "^(https?://)?[\\w.-]+(?:\\.[\\w\\.-]+)+[/#?]?.*$", 
             message = "Website URL must be valid")
    private String website; // Optional: brand website

    @Builder.Default
    private Boolean isActive = true; // Optional: default active
}
