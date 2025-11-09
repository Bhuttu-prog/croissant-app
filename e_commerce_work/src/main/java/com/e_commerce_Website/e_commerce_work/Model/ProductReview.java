package com.e_commerce_Website.e_commerce_work.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ProductReview implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Each review belongs to a single product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Optional: link to user who made the review
    @Column(nullable = false)
    @NotBlank(message = "Reviewer name is required")
    private String reviewerName; // If you don't have a User entity yet

    @Column(nullable = false)
    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot exceed 5")
    private Integer rating; // e.g., 1–5 stars

    @Column(nullable = false, length = 2000)
    @NotBlank(message = "Review text is required")
    @Size(min = 5, max = 2000, message = "Review must be between 5 and 2000 characters")
    private String review; // The actual review text

    @NotNull(message = "Approval status is required")
    private Boolean isApproved = false; // Admin can moderate reviews


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
}
