package com.e_commerce_Website.e_commerce_work.Controller.Category;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.e_commerce_Website.e_commerce_work.Service.Category.ICategoryService;
import com.e_commerce_Website.e_commerce_work.Controller.Product.ApiResponse;
import com.e_commerce_Website.e_commerce_work.Dto.Category.Request.CategoryRequestDto;
import com.e_commerce_Website.e_commerce_work.Dto.Category.Response.CategoryResponseDto;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Slf4j
@Validated
public class CategoryController {

    private final ICategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> createCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.createCategory(categoryRequestDto), "Category created successfully"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.updateCategory(id, categoryRequestDto), "Category updated successfully"));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.getCategoryById(id), "Category fetched successfully"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Category deleted successfully"));
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<CategoryResponseDto>>> getAllCategories() {
        return ResponseEntity.ok(ApiResponse.success(categoryService.getAllCategories(), "Categories fetched successfully"));
    }
}
