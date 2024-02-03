package peaksoft.giftlistj7g2.model.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "holidays")
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String holidayName;
    LocalDate createDate;
    String image;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "holiday")
    List<Gift> gift;

}
