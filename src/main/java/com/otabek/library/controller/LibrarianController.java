package com.otabek.library.controller;

import com.otabek.library.dto.ApiResponse;
import com.otabek.library.dto.LibrarianDto;
import com.otabek.library.service.LibrarianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("librarians")
@RequiredArgsConstructor
public class LibrarianController {

    private final LibrarianService librarianService;

    @PostMapping
    public ApiResponse<LibrarianDto> createLibrarian(@RequestBody @Valid LibrarianDto dto){
        return librarianService.createLibrarian(dto);
    }

    @GetMapping
    public ApiResponse<LibrarianDto> getLibrarianById(@RequestParam("id") Integer id){
        return librarianService.getLibrarianById(id);
    }

    @PutMapping
    public ApiResponse<LibrarianDto> updateLibrarianById(@RequestParam("id") Integer id,
                                                         @RequestBody LibrarianDto dto){
        return librarianService.updateLibrarianById(id, dto);
    }

    @DeleteMapping
    public ApiResponse<LibrarianDto> deleteLibrarianById(@RequestParam("id") Integer id){
        return librarianService.deleteLibrarianById(id);
    }

}
