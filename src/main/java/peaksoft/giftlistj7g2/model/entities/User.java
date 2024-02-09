package peaksoft.giftlistj7g2.model.entities;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;;
import peaksoft.giftlistj7g2.model.enums.Role;
import peaksoft.giftlistj7g2.model.enums.ShoesSize;
import peaksoft.giftlistj7g2.model.enums.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String lastName;
    int age;
    String email;
    String password;
    int telephone;
    LocalDate createDate;

    @Enumerated(EnumType.STRING)
    ShoesSize shoesSize;

    @Enumerated(EnumType.STRING)
    Role role;

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
}