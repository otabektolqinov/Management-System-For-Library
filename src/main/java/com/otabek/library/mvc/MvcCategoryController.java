package com.otabek.library.mvc;

import com.otabek.library.dto.ApiResponse;
import com.otabek.library.dto.CategoryDto;
import com.otabek.library.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("category")
public class MvcCategoryController {

    private final CategoryService categoryService;

    public MvcCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/create")
    public String createCategory(){
        return "create-category";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute CategoryDto categoryDto){
        categoryService.createCategory(categoryDto);
        return "redirect:/category/get-all";
    }

    @GetMapping("get-all")
    public String getAllCategories(Model model){
        ApiResponse<List<CategoryDto>> categories = categoryService.getAllCategories();
        List<CategoryDto> content = categories.getContent();
        model.addAttribute("categories", content);
        return "get-all-categories";
    }

    @GetMapping("/update/{id}")
    public String updateCategory(@PathVariable("id") Integer id, Model model){
        ApiResponse<CategoryDto> category = categoryService.getCategoryById(id);
        model.addAttribute("category", category.getContent());
        return "update-category";
    }

    @PostMapping("/update/{id}")
    public String updatedCategory(@PathVariable("id") Integer id, @ModelAttribute CategoryDto dto){
        categoryService.updateCategoryById(id, dto);
        return "redirect:/category/get-all";
    }


}
