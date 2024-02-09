package peaksoft.giftlistj7g2.model.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AuthResponse {
    private Long id;
    private String name;
    private String lastName;
    private String password;
    private String repeatPassword;
    private String email;
}
