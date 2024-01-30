package peaksoft.giftlistj7g2.model.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import peaksoft.giftlistj7g2.model.enums.Size;

import java.time.LocalDate;
@Entity
@Table(name = "gifts")
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String giftName;
    @OneToMany
    Holiday holiday;
    LocalDate createDate;
    String description;
    Size size;
    @OneToMany
    User user;
}
