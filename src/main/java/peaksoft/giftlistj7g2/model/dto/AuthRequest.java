package peaksoft.giftlistj7g2.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class AuthRequest {

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 30, message = "The name must not be less than 3 characters")
    private String name;

    @NotBlank(message = "Lastname cannot be empty")
    @Size(min = 3, max = 30, message = "NOT correct lastname")
    private String lastName;

    @NotBlank(message = "Password cannot be empty ")
    @Size(min = 8, max = 30, message = "NOT correct password")
    private String password;
    private String repeatPassword;

    @NotBlank(message = "Email  cannot be empty ")
    @Email(message = "NOT correct  email")
    private String email;
}