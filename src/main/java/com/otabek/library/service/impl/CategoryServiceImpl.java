package com.otabek.library.service.impl;

import com.otabek.library.dto.ApiResponse;
import com.otabek.library.dto.CategoryDto;
import com.otabek.library.exceptions.ContentNotFoundException;
import com.otabek.library.exceptions.DatabaseException;
import com.otabek.library.model.Category;
import com.otabek.library.repository.CategoryRepository;
import com.otabek.library.service.CategoryService;
import com.otabek.library.service.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public ApiResponse<CategoryDto> createCategory(CategoryDto dto) {
        try {
            Category entity = categoryMapper.toEntity(dto);
            Category saved = categoryRepository.save(entity);

            return ApiResponse.<CategoryDto>builder()
                    .success(true)
                    .message("ok")
                    .content(categoryMapper.toDto(saved))
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public ApiResponse<CategoryDto> getCategoryById(Integer id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isEmpty()){
            throw new ContentNotFoundException(String.format("Category with %d is not found", id));
        }
        return ApiResponse.<CategoryDto>builder()
                .success(true)
                .message("ok")
                .content(categoryMapper.toDtoWithAllEntity(optional.get()))
                .build();
    }

    @Override
    public ApiResponse<CategoryDto> updateCategoryById(Integer id, CategoryDto dto) {
        try {
            Optional<Category> optional = categoryRepository.findById(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("Category with %d is not found", id));
            }
            Category category = categoryMapper.updateAllFields(optional.get(), dto);
            Category saved = categoryRepository.save(category);
            return ApiResponse.<CategoryDto>builder()
                    .success(true)
                    .message("ok")
                    .content(categoryMapper.toDto(saved))
                    .build();
        } catch (ContentNotFoundException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public ApiResponse<CategoryDto> deleteCategoryById(Integer id) {
        try {
            Optional<Category> optional = categoryRepository.findById(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("Category with %d id is not found", id));
            }
            categoryRepository.deleteById(id);
            return ApiResponse.<CategoryDto>builder()
                    .message("Successfully deleted category")
                    .success(true)
                    .build();
        } catch (ContentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ApiResponse<List<CategoryDto>> getAllCategories() {
        try {
            List<Category> all = categoryRepository.findAll();
            List<CategoryDto> categoryDtoList = categoryMapper.toDtoList(all);
            return ApiResponse.<List<CategoryDto>>builder()
                    .success(true)
                    .content(categoryDtoList)
                    .message("ok")
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
