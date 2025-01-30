package com.otabek.library.service;

import com.otabek.library.dto.ApiResponse;
import com.otabek.library.dto.LibrarianDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LibrarianService {
    ApiResponse<LibrarianDto> createLibrarian(LibrarianDto dto);
    ApiResponse<LibrarianDto> getLibrarianById(Integer id);
    ApiResponse<LibrarianDto> updateLibrarianById(Integer id, LibrarianDto dto);
    ApiResponse<LibrarianDto> deleteLibrarianById(Integer id);

    ApiResponse<List<LibrarianDto>> getAllLibrarians();
}
