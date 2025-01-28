package com.otabek.library.controller;

import com.otabek.library.dto.ApiResponse;
import com.otabek.library.dto.CategoryDto;
import com.otabek.library.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ApiResponse<CategoryDto> createCategory(@RequestBody CategoryDto dto){
        return this.categoryService.createCategory(dto);
    }

    @GetMapping
    public ApiResponse<CategoryDto> getCategoryById(@RequestParam("id") Integer id){
        return this.categoryService.getCategoryById(id);
    }

    @PutMapping
    public ApiResponse<CategoryDto> updateCategoryById(@RequestParam("id") Integer id,
                                                       @RequestBody CategoryDto dto){
        return this.categoryService.updateCategoryById(id, dto);
    }

    @DeleteMapping
    public ApiResponse<CategoryDto> deleteCategoryById(@RequestParam("id") Integer id){
        return this.categoryService.deleteCategoryById(id);
    }

}
