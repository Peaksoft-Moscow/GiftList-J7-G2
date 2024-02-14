package peaksoft.giftlistj7g2.dto.dto;

import lombok.Getter;
import lombok.Setter;
import peaksoft.giftlistj7g2.model.enums.Role;

@Getter
@Setter
public class AuthResponse {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Role role;
}