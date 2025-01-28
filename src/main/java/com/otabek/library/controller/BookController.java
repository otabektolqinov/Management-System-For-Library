package com.otabek.library.controller;

import com.otabek.library.dto.ApiResponse;
import com.otabek.library.dto.BookDto;
import com.otabek.library.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ApiResponse<BookDto> createBook(@RequestBody @Valid BookDto dto){
        return this.bookService.createBook(dto);
    }

    @GetMapping
    public ApiResponse<BookDto> getBookById(@RequestParam("id") Integer id){
        return this.bookService.getBookById(id);
    }

    @PutMapping
    public ApiResponse<BookDto> updateBookById(@RequestParam("id") Integer id,
                                               @RequestBody BookDto dto){
        return this.bookService.updateBookById(id, dto);
    }

    @DeleteMapping
    public ApiResponse<BookDto> deleteBookById(@RequestParam("id") Integer id){
        return this.bookService.deleteBookById(id);
    }

    @GetMapping("get-all")
    public ApiResponse<List<BookDto>> getAllBooks(){
        return this.bookService.getAllBooks();
    }

}
