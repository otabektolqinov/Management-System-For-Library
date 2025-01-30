package com.otabek.library.mvc;

import com.otabek.library.dto.ApiResponse;
import com.otabek.library.dto.LibrarianDto;
import com.otabek.library.service.LibrarianService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("librarian")
public class MvcLibrarianController {

    private final LibrarianService librarianService;

    @GetMapping("/create")
    public String createLibrarian(){
        return "create-librarian";
    }

    @PostMapping("/save")
    public String saveLibrarian(@ModelAttribute LibrarianDto dto){
        librarianService.createLibrarian(dto);
        return "redirect:/librarian/get-all";
    }

    @GetMapping("get-all")
    public String getAllLibrarians(Model model){
        ApiResponse<List<LibrarianDto>> allLibrarians = librarianService.getAllLibrarians();
        model.addAttribute("librarians", allLibrarians.getContent());
        return "get-all-librarians";
    }

    @GetMapping("/update/{id}")
    public String updateLibrarian(@PathVariable("id") Integer id, Model model){
        ApiResponse<LibrarianDto> librarianById = librarianService.getLibrarianById(id);
        LibrarianDto content = librarianById.getContent();
        model.addAttribute("librarian", content);
        return "update-librarian";
    }

    @PostMapping("/update/{id}")
    public String updatedLibrarian(@PathVariable("id") Integer id, @ModelAttribute LibrarianDto dto){
        librarianService.updateLibrarianById(id, dto);
        return "redirect:/librarian/get-all";
    }

    @GetMapping("/delete/{id}")
    public String deleteLibrarian(@PathVariable("id") Integer id){
        librarianService.deleteLibrarianById(id);
        return "redirect:/librarian/get-all";
    }
}
