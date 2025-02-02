package com.otabek.library.dto;

import com.otabek.library.utils.PhoneValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {

    private Integer id;
    @NotBlank(message = "name cannot be blank")
    @NotNull(message = "name cannot be null")
    private String name;
    @NotBlank(message = "email cannot be blank")
    @NotNull(message = "email cannot be null")
    @Email
    private String email;
    @PhoneValidation(message = "invalid phone number")
    private String phone;
    @NotNull(message = "password cannot be null")
    @NotBlank(message = "password cannot be blank")
    private String password;
    @NotNull(message = "membership cannot be null")
    private String membership;
    private String role;

}
