package com.otabek.library.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    private String message;
    private T content;
    private int code;
    private boolean success;
    private List<ErrorDto> errors;

}
