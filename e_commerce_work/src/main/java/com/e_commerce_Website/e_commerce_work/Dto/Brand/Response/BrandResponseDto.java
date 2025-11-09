package com.e_commerce_Website.e_commerce_work.Dto.Brand.Response;

import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.e_commerce_Website.e_commerce_work.Model.Brand;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String description;

    private String logoUrl; // Brand logo

    private String website; // Brand website URL

    private Boolean isActive; // Brand status

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String createdBy;

    private String updatedBy;

    public BrandResponseDto(Brand brand) {      
        if (brand != null) {
            this.id = brand.getId();
            this.name = brand.getName();
            this.description = brand.getDescription();
            this.logoUrl = brand.getLogoUrl();
            this.website = brand.getWebsite();
            this.isActive = brand.getIsActive();
            this.createdAt = brand.getCreatedAt();
            this.updatedAt = brand.getUpdatedAt();
            this.createdBy = brand.getCreatedBy();
            this.updatedBy = brand.getUpdatedBy();
        }
    }
}
