package com.e_commerce_Website.e_commerce_work.Dto.Category.Response;

import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.e_commerce_Website.e_commerce_work.Model.Category;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String description;

    private String slug; // SEO-friendly URL

    private String imageUrl; // Optional category image

    private Boolean isActive; // Category status

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String createdBy;

    private String updatedBy;

    public CategoryResponseDto(Category category) {
        if (category != null) {
            this.id = category.getId();
            this.name = category.getName();
            this.description = category.getDescription();
            this.slug = category.getSlug();
            this.imageUrl = category.getImageUrl();
            this.isActive = category.getIsActive();
            this.createdAt = category.getCreatedAt();
            this.updatedAt = category.getUpdatedAt();
            this.createdBy = category.getCreatedBy();
            this.updatedBy = category.getUpdatedBy();
        }
    }
}
