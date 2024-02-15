package peaksoft.giftlistj7g2.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import peaksoft.giftlistj7g2.model.enums.Role;
import peaksoft.giftlistj7g2.model.enums.ShoesSize;
import peaksoft.giftlistj7g2.model.enums.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String lastName;
    @Column(unique = true)
    String email;
    String password;
    int age;
    int telephone;
    LocalDate createDate;
    @Enumerated(EnumType.STRING)
    ShoesSize shoesSize;
    @Enumerated(EnumType.STRING)
    Size size;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<Notification> notification;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<Gift> gifts;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<Complaint> complaint;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<Mailing> mailings;
    @Enumerated(EnumType.STRING)
    Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
