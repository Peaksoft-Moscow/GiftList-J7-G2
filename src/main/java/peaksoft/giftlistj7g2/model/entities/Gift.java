package peaksoft.giftlistj7g2.model.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import peaksoft.giftlistj7g2.model.enums.Reservation;
import peaksoft.giftlistj7g2.model.enums.Size;

import java.time.LocalDate;
import java.util.List;

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
    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    Holiday holiday;
    LocalDate createDate;
    String description;
    String image;
    @Enumerated
    Reservation reservation;
    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    User user;
}
