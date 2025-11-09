package com.e_commerce_Website.e_commerce_work.Service.Category;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;   // ✅ Correct import for Spring caching
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import com.e_commerce_Website.e_commerce_work.Dto.Category.Request.CategoryRequestDto;
import com.e_commerce_Website.e_commerce_work.Dto.Category.Response.CategoryResponseDto;
import com.e_commerce_Website.e_commerce_work.Exception.Category.CategoryAlreadyExistsException;
import com.e_commerce_Website.e_commerce_work.Exception.Category.CategoryNotFoundException;
import com.e_commerce_Website.e_commerce_work.Model.Category;
import com.e_commerce_Website.e_commerce_work.Repository.Category.CategoryRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@Validated
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    // ✅ Create a new category — clear all cache entries after creation
    @Override
    @Transactional
    @CacheEvict(value = "categories", allEntries = true)
    public CategoryResponseDto createCategory(@Valid CategoryRequestDto categoryRequestDto) {
        log.info("Creating category: {}", categoryRequestDto);

        Optional<Category> existingCategory = categoryRepository.findByName(categoryRequestDto.getName());
        if (existingCategory.isPresent()) {
            throw new CategoryAlreadyExistsException("Category already exists", categoryRequestDto.getName());
        }

        Category newCategory = new Category();
        newCategory.setName(categoryRequestDto.getName());
        newCategory.setDescription(categoryRequestDto.getDescription());
        newCategory.setSlug(categoryRequestDto.getSlug());
        newCategory.setIsActive(categoryRequestDto.getIsActive());

        Category savedCategory = categoryRepository.save(newCategory);
        return new CategoryResponseDto(savedCategory);
    }

    // ✅ Update a category — evict cache only for this ID
    @Override
    @Transactional
    @CacheEvict(value = "categories", key = "#id")
    public CategoryResponseDto updateCategory(
            @Positive @Min(1) @Max(1000000) Long id,
            @Valid CategoryRequestDto categoryRequestDto) {

        log.info("Updating category: {}", id);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found", id));

        category.setName(categoryRequestDto.getName());
        category.setDescription(categoryRequestDto.getDescription());
        category.setSlug(categoryRequestDto.getSlug());
        category.setIsActive(categoryRequestDto.getIsActive());

        Category updatedCategory = categoryRepository.save(category);
        return new CategoryResponseDto(updatedCategory);
    }

    // ✅ Get category by ID — cache this category
    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "categories", key = "#id")
    public CategoryResponseDto getCategoryById(
            @Positive @Min(1) @Max(1000000) Long id) {

        log.info("Getting category by id: {}", id);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found", id));

        return new CategoryResponseDto(category);
    }

    // ❌ No caching needed yet (you can add later if required)
    @Override
    @Transactional
    @CacheEvict(value = "categories", key = "#id") // Clear cache for this category
    public void deleteCategory(@Positive @Min(1) @Max(1000000) Long id) {
        log.info("Deleting category: {}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found", id));
        categoryRepository.delete(category);
    }

    // ❌ You can add caching for this later if needed
    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "categories") // Cache the full category list
    public List<CategoryResponseDto> getAllCategories() {
        log.info("Getting all categories");
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(CategoryResponseDto::new)
                .collect(Collectors.toList());
    }

    // Not implemented yet (you’ll handle later)
    @Override
    public List<CategoryResponseDto> searchCategories(
            @NotBlank(message = "Keyword cannot be blank")
            @Size(min = 2, max = 100, message = "Keyword must be between 2 and 100 characters") String keyword) {
        throw new UnsupportedOperationException("Unimplemented method 'searchCategories'");
    }

    @Override
    public List<CategoryResponseDto> getActiveCategories() {
        throw new UnsupportedOperationException("Unimplemented method 'getActiveCategories'");
    }

    @Override
    public CategoryResponseDto addImageToCategory(
            @Positive @Min(1) @Max(1000000) Long id,
            @NotNull MultipartFile image) {
        throw new UnsupportedOperationException("Unimplemented method 'addImageToCategory'");
    }

    @Override
    public CategoryResponseDto removeImageFromCategory(
            @Positive @Min(1) @Max(1000000) Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'removeImageFromCategory'");
    }

    @Override
    public CategoryResponseDto getImageFromCategory(
            @Positive @Min(1) @Max(1000000) Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'getImageFromCategory'");
    }

    @Override
    public String showImageUrlFromCategory(
            @NotBlank(message = "File name cannot be blank") String fileName) {
        throw new UnsupportedOperationException("Unimplemented method 'showImageUrlFromCategory'");
    }
}
