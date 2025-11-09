package com.e_commerce_Website.e_commerce_work.Service.Category;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import com.e_commerce_Website.e_commerce_work.Dto.Category.Request.CategoryRequestDto;
import com.e_commerce_Website.e_commerce_work.Dto.Category.Response.CategoryResponseDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * Category Service Interface
 * Handles all category-related operations such as CRUD, search, and filtering.
 * 
 * @author 
 * @version 1.0
 */
@Validated
public interface ICategoryService {

    /**
     * Creates a new category.
     *
     * @param categoryRequestDto category creation details
     * @return CategoryResponseDto created category details
     */
    CategoryResponseDto createCategory(@Valid CategoryRequestDto categoryRequestDto);

    /**
     * Updates an existing category.
     *
     * @param id category ID
     * @param categoryRequestDto category update details
     * @return CategoryResponseDto updated category details
     */
    CategoryResponseDto updateCategory(
            @Positive @Min(1) @Max(1000000) Long id,
            @Valid CategoryRequestDto categoryRequestDto);

    /**
     * Gets category details by ID.
     *
     * @param id category ID
     * @return CategoryResponseDto category details
     */
    CategoryResponseDto getCategoryById(
            @Positive @Min(1) @Max(1000000) Long id);

    /**
     * Deletes a category by ID.
     *
     * @param id category ID
     */
    void deleteCategory(
            @Positive @Min(1) @Max(1000000) Long id);

    /**
     * Fetches all categories.
     *
     * @return list of CategoryResponseDto
     */
    
     /**
     \* Add image to category
     * @param id category ID
     * @param image image file
     * @return CategoryResponseDto category details
     */
    CategoryResponseDto addImageToCategory(
            @Positive @Min(1) @Max(1000000) Long id,
            @NotNull MultipartFile image);

    /**
     * Remove image from category
     * @param id category ID
     * @return CategoryResponseDto category details
     */
    CategoryResponseDto removeImageFromCategory(
            @Positive @Min(1) @Max(1000000) Long id);


    /**
     * Get image from category
     * @param id category ID
     * @return CategoryResponseDto category details
     */
    CategoryResponseDto getImageFromCategory(
            @Positive @Min(1) @Max(1000000) Long id);

     /** show image url from category */
     /**
    * Show image url from category
    * @param fileName file name
   * @return String image url from category
    */
     String showImageUrlFromCategory(
     @NotBlank(message = "File name cannot be blank") String fileName);

    /**
     * Gets all categories.
     *
     * @return list of CategoryResponseDto
     */
      
    List<CategoryResponseDto> getAllCategories();

    /**
     * Searches categories by keyword.
     *
     * @param keyword keyword for search
     * @return list of CategoryResponseDto
     */
    List<CategoryResponseDto> searchCategories(
            @NotBlank(message = "Keyword cannot be blank")
            @Size(min = 2, max = 100, message = "Keyword must be between 2 and 100 characters")
            String keyword);

    /**
     * Gets all active categories.
     *
     * @return list of active categories
     */
    List<CategoryResponseDto> getActiveCategories();
}
