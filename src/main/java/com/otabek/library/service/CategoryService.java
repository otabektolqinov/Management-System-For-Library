package com.otabek.library.service;

import com.otabek.library.dto.ApiResponse;
import com.otabek.library.dto.CategoryDto;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    ApiResponse<CategoryDto> createCategory(CategoryDto dto);
    ApiResponse<CategoryDto> getCategoryById(Integer id);
    ApiResponse<CategoryDto> updateCategoryById(Integer id, CategoryDto dto);
    ApiResponse<CategoryDto> deleteCategoryById(Integer id);
}
