package com.e_commerce_Website.e_commerce_work.Service.Product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.e_commerce_Website.e_commerce_work.Repository.Product.ProductRepository;
import com.e_commerce_Website.e_commerce_work.Repository.Category.CategoryRepository;
import com.e_commerce_Website.e_commerce_work.Repository.Brand.BrandRepository;
import com.e_commerce_Website.e_commerce_work.Model.Category;
import com.e_commerce_Website.e_commerce_work.Model.Brand;
import com.e_commerce_Website.e_commerce_work.Model.Product;
import com.e_commerce_Website.e_commerce_work.Dto.Product.Request.ProductRequestDto;
import com.e_commerce_Website.e_commerce_work.Dto.Product.Response.ProductResponseDto;
import com.e_commerce_Website.e_commerce_work.Exception.Product.ProductAlreadyExistsException;
import com.e_commerce_Website.e_commerce_work.Exception.Product.ProductNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@Validated
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    // -------------------- CREATE --------------------
    @Override
    @Transactional
    @CacheEvict(value = "products", allEntries = true) // Clear all products cache after creation
    public ProductResponseDto createProduct(@Valid ProductRequestDto productRequestDto) {
        log.info("Creating product: {}", productRequestDto);
        productRepository.findByName(productRequestDto.getName())
                .ifPresent(p -> {
                    throw new ProductAlreadyExistsException("Product already exists", productRequestDto.getName());
                });

        Category category = categoryRepository.findByName(productRequestDto.getCategory().getName())
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(productRequestDto.getCategory().getName());
                    newCategory.setDescription(productRequestDto.getCategory().getDescription());
                    newCategory.setSlug(productRequestDto.getCategory().getSlug());
                    newCategory.setIsActive(productRequestDto.getCategory().getIsActive());
                    newCategory.setCreatedAt(LocalDateTime.now());
                    newCategory.setUpdatedAt(LocalDateTime.now());
                    newCategory.setCreatedBy("System");
                    newCategory.setUpdatedBy("System");
                    return categoryRepository.save(newCategory);
                });

        Brand brand = brandRepository.findByName(productRequestDto.getBrand().getName())
                .orElseGet(() -> {
                    Brand newBrand = new Brand();
                    newBrand.setName(productRequestDto.getBrand().getName());
                    newBrand.setDescription(productRequestDto.getBrand().getDescription());
                    newBrand.setIsActive(productRequestDto.getBrand().getIsActive());
                    newBrand.setCreatedAt(LocalDateTime.now());
                    newBrand.setUpdatedAt(LocalDateTime.now());
                    newBrand.setCreatedBy("System");
                    newBrand.setUpdatedBy("System");
                    return brandRepository.save(newBrand);
                });

        Product newProduct = new Product();
        newProduct.setName(productRequestDto.getName());
        newProduct.setDescription(productRequestDto.getDescription());
        newProduct.setPrice(productRequestDto.getPrice());
        newProduct.setDiscountedPrice(productRequestDto.getDiscountedPrice());
        newProduct.setCurrency(productRequestDto.getCurrency());
        newProduct.setStockQuantity(productRequestDto.getStockQuantity());
        newProduct.setIsAvailable(productRequestDto.getIsAvailable());
        newProduct.setSku(productRequestDto.getSku());
        newProduct.setSlug(productRequestDto.getSlug());
        newProduct.setTags(productRequestDto.getTags());
        newProduct.setAverageRating(0.0);
        newProduct.setReviewCount(0);
        newProduct.setIsActive(true);
        newProduct.setCategory(category);
        newProduct.setBrand(brand);
        newProduct.setCreatedAt(LocalDateTime.now());
        newProduct.setUpdatedAt(LocalDateTime.now());
        newProduct.setCreatedBy("System");
        newProduct.setUpdatedBy("System");

        Product savedProduct = productRepository.save(newProduct);
        return new ProductResponseDto(savedProduct);
    }

    // -------------------- UPDATE --------------------
    @Override
    @Transactional
    @CacheEvict(value = "products", key = "#id") // Clear cache for this product
    public ProductResponseDto updateProduct(@Positive @Max(1000000) @Min(1) Long id, @Valid ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found", id));

        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setDiscountedPrice(productRequestDto.getDiscountedPrice());
        product.setCurrency(productRequestDto.getCurrency());
        product.setStockQuantity(productRequestDto.getStockQuantity());
        product.setIsAvailable(productRequestDto.getIsAvailable());
        product.setSku(productRequestDto.getSku());
        product.setSlug(productRequestDto.getSlug());
        product.setTags(productRequestDto.getTags());
        product.setAverageRating(0.0);
        product.setReviewCount(0);
        product.setIsActive(true);
        product.setUpdatedAt(LocalDateTime.now());
        product.setUpdatedBy("System");

        Product updatedProduct = productRepository.save(product);
        return new ProductResponseDto(updatedProduct);
    }

    // -------------------- GET BY ID --------------------
    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "products", key = "#productId") // Cache this product
    public ProductResponseDto getProductById(@Positive @Max(1000000) @Min(1) Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found", productId));
        return new ProductResponseDto(product);
    }

    // -------------------- DELETE --------------------
    @Override
    @Transactional
    @CacheEvict(value = "products", key = "#productId") // Clear cache for this product
    public void deleteProduct(@Positive @Max(1000000) @Min(1) Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found", productId));
        productRepository.delete(product);
    }

    // -------------------- GET ALL --------------------
    @Override
    @Cacheable(value = "products") // Cache the full product list
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> searchProducts(
            @NotBlank(message = "Keyword cannot be blank") @Size(min = 2, max = 100, message = "Keyword must be between 2 and 100 characters") String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchProducts'");
    }

    @Override
    public List<ProductResponseDto> getProductsByCategory(@Positive @Max(1000000) @Min(1) Long categoryId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductsByCategory'");
    }

    @Override
    public List<ProductResponseDto> getProductsByBrand(Long brandId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductsByBrand'");
    }

    @Override
    public List<ProductResponseDto> getTopRatedProducts(int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTopRatedProducts'");
    }

    @Override
    public List<ProductResponseDto> getLatestProducts(int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLatestProducts'");
    }

    @Override
    public List<ProductResponseDto> getProductsByCategoryAndBrand(Long categoryId, Long brandId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductsByCategoryAndBrand'");
    }

    @Override
    public List<ProductResponseDto> getProductsByCategoryAndBrandAndPriceRange(Long categoryId, Long brandId,
            BigDecimal priceRange) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductsByCategoryAndBrandAndPriceRange'");
    }

    @Override
    public List<ProductResponseDto> getProductsByCategoryAndBrandAndPriceRangeAndSortBy(Long categoryId, Long brandId,
            BigDecimal priceRange, String sortBy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductsByCategoryAndBrandAndPriceRangeAndSortBy'");
    }

    // The other methods remain unchanged (throwing UnsupportedOperationException)
}
