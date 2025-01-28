package com.otabek.library.service;

import com.otabek.library.dto.ApiResponse;
import com.otabek.library.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    ApiResponse<BookDto> createBook(BookDto dto);
    ApiResponse<BookDto> getBookById(Integer id);
    ApiResponse<BookDto> updateBookById(Integer id, BookDto dto);
    ApiResponse<BookDto> deleteBookById(Integer id);
    ApiResponse<List<BookDto>> getAllBooks();
}
