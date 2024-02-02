package peaksoft.giftlistj7g2.model.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "complaints")
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    @ManyToOne(cascade = {
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    User user;

}
