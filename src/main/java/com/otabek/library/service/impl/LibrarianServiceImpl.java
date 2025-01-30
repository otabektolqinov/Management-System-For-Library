package com.otabek.library.service.impl;

import com.otabek.library.dto.ApiResponse;
import com.otabek.library.dto.LibrarianDto;
import com.otabek.library.exceptions.ContentNotFoundException;
import com.otabek.library.exceptions.DatabaseException;
import com.otabek.library.model.Librarian;
import com.otabek.library.repository.LibrarianRepository;
import com.otabek.library.service.LibrarianService;
import com.otabek.library.service.mapper.LibrarianMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LibrarianServiceImpl implements LibrarianService {

    private final LibrarianMapper librarianMapper;
    private final LibrarianRepository librarianRepository;

    @Override
    public ApiResponse<LibrarianDto> createLibrarian(LibrarianDto dto) {
        try {
            Librarian librarian = librarianMapper.toEntity(dto);
            Librarian saved = librarianRepository.save(librarian);
            return ApiResponse.<LibrarianDto>builder()
                    .success(true)
                    .message("OK")
                    .content(librarianMapper.toDto(saved))
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public ApiResponse<LibrarianDto> getLibrarianById(Integer id) {
        Optional<Librarian> optional = librarianRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()){
            throw new ContentNotFoundException(String.format("Librarian with %d id is not found", id));
        }
        return ApiResponse.<LibrarianDto>builder()
                .success(true)
                .content(librarianMapper.toDto(optional.get()))
                .message("OK")
                .build();
    }

    @Override
    public ApiResponse<LibrarianDto> updateLibrarianById(Integer id, LibrarianDto dto) {
        Optional<Librarian> optional = librarianRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()){
            throw new ContentNotFoundException(String.format("Librarian with %d id is not found", id));
        }
        Librarian librarian = librarianMapper.updateAllFields(optional.get(), dto);
        Librarian saved = librarianRepository.save(librarian);

        return ApiResponse.<LibrarianDto>builder()
                .success(true)
                .content(librarianMapper.toDto(saved))
                .message("Successfully updated")
                .build();
    }

    @Override
    public ApiResponse<LibrarianDto> deleteLibrarianById(Integer id) {
        Optional<Librarian> optional = librarianRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()){
            throw new ContentNotFoundException(String.format("Librarian with %d id is not found", id));
        }

        Librarian librarian = optional.get();
        librarian.setDeletedAt(LocalDateTime.now());
        librarianRepository.save(librarian);
        return ApiResponse.<LibrarianDto>builder()
                .success(true)
                .message("Successfully deleted")
                .build();
    }

    @Override
    public ApiResponse<List<LibrarianDto>> getAllLibrarians() {
        try {
            List<Librarian> librarians = librarianRepository.findAllByDeletedAtIsNull();
            List<LibrarianDto> librarianDtoList = librarianMapper.toDtoList(librarians);
            return ApiResponse.<List<LibrarianDto>>builder()
                    .message("ok")
                    .content(librarianDtoList)
                    .success(true)
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
