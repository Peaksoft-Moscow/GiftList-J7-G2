package peaksoft.giftlistj7g2.model.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "mailings")
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Mailing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String image;
    String name;
    String text;
    @ManyToOne(cascade = {
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    User user;
}
