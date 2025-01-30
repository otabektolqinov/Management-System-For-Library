package com.otabek.library.service.impl;

import com.otabek.library.dto.ApiResponse;
import com.otabek.library.dto.BookDto;
import com.otabek.library.exceptions.ContentNotFoundException;
import com.otabek.library.exceptions.DatabaseException;
import com.otabek.library.model.AvailabilityStatus;
import com.otabek.library.model.Book;
import com.otabek.library.repository.BookRepository;
import com.otabek.library.service.BookService;
import com.otabek.library.service.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public ApiResponse<BookDto> createBook(BookDto dto) {
        try {
            Book book = bookMapper.toEntity(dto);
            book.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
            Book saved = bookRepository.save(book);
            return ApiResponse.<BookDto>builder()
                    .content(bookMapper.toDto(saved))
                    .message("ok")
                    .success(true)
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public ApiResponse<BookDto> getBookById(Integer id) {
        try {
            Optional<Book> optional = bookRepository.findById(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("Book with %d id is not found", id));
            }

            return ApiResponse.<BookDto>builder()
                    .content(bookMapper.toDto(optional.get()))
                    .message("ok")
                    .success(true)
                    .build();
        } catch (ContentNotFoundException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public ApiResponse<BookDto> updateBookById(Integer id, BookDto dto) {
        try {
            Optional<Book> optional = bookRepository.findById(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("Book with %d id is not found", id));
            }

            System.out.println("a");
            System.out.println("a");
            System.out.println("a");
            System.out.println("a");
            System.out.println("a");
            System.out.println("a");
            System.out.println("a");
            System.out.println("a");
            Book book = bookMapper.updateAllFields(optional.get(), dto);
            Book saved = bookRepository.save(book);

            return ApiResponse.<BookDto>builder()
                    .success(true)
                    .message("Successfully updated")
                    .content(bookMapper.toDto(saved))
                    .build();
        } catch (ContentNotFoundException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public ApiResponse<BookDto> deleteBookById(Integer id) {
        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isEmpty()){
            throw new ContentNotFoundException(String.format("Book with %d id is not found", id));
        }
        bookRepository.deleteById(id);
        return ApiResponse.<BookDto>builder()
                .message("successfully deleted")
                .success(true)
                .build();
    }

    @Override
    public ApiResponse<List<BookDto>> getAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        if (bookList.isEmpty()){
            throw new ContentNotFoundException("Book list is empty");
        }
        List<BookDto> bookDtoList = bookMapper.toDtoList(bookList);

        return ApiResponse.<List<BookDto>>builder()
                .message("ok")
                .content(bookDtoList)
                .success(true)
                .build();
    }
}
