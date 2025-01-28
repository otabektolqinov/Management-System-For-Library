package com.otabek.library.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {

    private Integer id;
    @NotBlank(message = "title cannot be blank")
    @NotNull(message = "title cannot be null")
    private String title;
    @NotBlank(message = "author cannot be blank")
    @NotNull(message = "author cannot be null")
    private String author;
    @NotBlank(message = "isbn cannot be blank")
    @NotNull(message = "isbn cannot be null")
    private String isbn;
    @NotBlank(message = "publisher cannot be blank")
    @NotNull(message = "publisher cannot be null")
    private String publisher;
    @Positive(message = "count should be positive number")
    private Integer count;
    @NotNull(message = "pageCount cannot be null")
    @Positive(message = "pageCount should be positive")
    private Integer pageCount;
    private LocalDate publicationDate;
    @NotBlank(message = "category cannot be blank")
    @NotNull(message = "category cannot be null")
    private String category;
    private String availabilityStatus;
    private String imageUrl;


}
