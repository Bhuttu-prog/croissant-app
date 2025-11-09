package com.e_commerce_Website.e_commerce_work.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "brands", uniqueConstraints = {
    @UniqueConstraint(name = "name_unique", columnNames = "name")
}, indexes = {
    @Index(name = "idx_brand_name", columnList = "name"),
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Brand implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // Example: "Nike", "Apple", "Samsung"

    @Column(length = 1000)
    private String description;

    private String logoUrl; // Brand logo image

    private String website; // Optional official brand website

    private Boolean isActive = true; // For enabling/disabling brands

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products; // One brand -> many products

    
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
