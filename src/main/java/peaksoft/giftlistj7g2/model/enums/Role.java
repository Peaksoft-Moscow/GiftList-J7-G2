package peaksoft.giftlistj7g2.model.enums;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;


public enum Role implements GrantedAuthority {
    ADMIN(),
    USER();

    @Override
    public String getAuthority() {
        return name();
    }

}

