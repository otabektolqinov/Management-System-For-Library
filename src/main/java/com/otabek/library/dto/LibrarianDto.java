package com.otabek.library.dto;

import com.otabek.library.utils.PhoneValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibrarianDto {

    private Integer id;
    @NotBlank(message = "name cannot be blank")
    @NotNull(message = "name cannot be null")
    private String name;
    @Email
    @NotBlank(message = "email cannot be blank")
    @NotNull(message = "email cannot be null")
    private String email;
    @PhoneValidation(message = "invalid phone number")
    private String phone;
    private String role;

}
