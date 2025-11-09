package com.e_commerce_Website.e_commerce_work.Service.Product;

import com.e_commerce_Website.e_commerce_work.Dto.Product.Request.ProductRequestDto;
import com.e_commerce_Website.e_commerce_work.Dto.Product.Request.ProductUpdateRequestDto;
import com.e_commerce_Website.e_commerce_work.Dto.Product.Response.ProductResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal; 

import java.util.List;

public interface IProductService {

    /**
     * Create a new product
     * @param productRequestDto DTO containing product data
     * @return ProductResponseDto with saved product data
     */ 
    ProductResponseDto createProduct(@Valid ProductRequestDto productRequestDto);

    /**
     * Update an existing product
     * @param productRequestDto DTO containing updated product data
     * @return ProductResponseDto with updated product data
     */
    ProductResponseDto updateProduct(@Positive @Max(value = 1000000) @Min(value = 1) Long id, @Valid ProductRequestDto productRequestDto);

    /**
     * Get product by ID
     * @param productId Product ID
     * @return ProductResponseDto
     */
    ProductResponseDto getProductById( @Positive @Max(value = 1000000) @Min(value = 1) Long productId);

    /**
     * Delete a product by ID
     * @param productId Product ID
     */
    void deleteProduct( @Positive @Max(value = 1000000) @Min(value = 1) Long productId);

    /**
     * Get all products
     * @return List of ProductResponseDto
     */
    List<ProductResponseDto> getAllProducts();

    /**
     * Search products by name, category, or brand
     * @param keyword Search keyword
     * @return List of ProductResponseDto
     */
    List<ProductResponseDto> searchProducts( @NotBlank(message = "Keyword cannot be blank") @Size(min = 2, max = 100, message = "Keyword must be between 2 and 100 characters") String keyword);

    /**
     * Get products by category ID
     * @param categoryId Category ID
     * @return List of ProductResponseDto
     */
    List<ProductResponseDto> getProductsByCategory( @Positive @Max(value = 1000000) @Min(value = 1) Long categoryId);

    /**
     * Get products by brand ID
     * @param brandId Brand ID
     * @return List of ProductResponseDto
     */
    List<ProductResponseDto> getProductsByBrand(Long brandId);

    /**
     * Get top-rated products
     * @param limit Number of products to return
     * @return List of ProductResponseDto
     */
    List<ProductResponseDto> getTopRatedProducts(int limit);

    /**
     * Get latest products
     * @param limit Number of products to return
     * @return List of ProductResponseDto
     */
    List<ProductResponseDto> getLatestProducts(int limit);

    /**
     * Get products by category and brand
     * @param categoryId Category ID
     * @param brandId Brand ID
     * @return List of ProductResponseDto
     */
    List<ProductResponseDto> getProductsByCategoryAndBrand(Long categoryId, Long brandId);

    /**
     * Get products by category and brand and price range
     * @param categoryId Category ID
     * @param brandId Brand ID
     * @param priceRange Price range
     * @return List of ProductResponseDto
     */
    List<ProductResponseDto> getProductsByCategoryAndBrandAndPriceRange(Long categoryId, Long brandId, BigDecimal priceRange);


    /**
     * Get products by category and brand and price range and sort by price
     * @param categoryId Category ID
     * @param brandId Brand ID
     * @param priceRange Price range
     * @param sortBy Sort by
     * @return List of ProductResponseDto
     */
    List<ProductResponseDto> getProductsByCategoryAndBrandAndPriceRangeAndSortBy(Long categoryId, Long brandId, BigDecimal priceRange, String sortBy);


}
