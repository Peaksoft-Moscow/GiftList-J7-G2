package peaksoft.giftlistj7g2.model.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import peaksoft.giftlistj7g2.model.enums.Role;
import peaksoft.giftlistj7g2.model.enums.ShoesSize;
import peaksoft.giftlistj7g2.model.enums.Size;

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
     int telephone;

     @OneToMany(cascade = CascadeType.ALL)
     List<Notification> notification;
     @ManyToOne(cascade = {
             CascadeType.DETACH,
             CascadeType.MERGE,
             CascadeType.PERSIST,
             CascadeType.REFRESH})
     Complaint complaint;
     @OneToMany(cascade = CascadeType.ALL)
     List<Gift> gifts;
     @Enumerated(EnumType.ORDINAL)
     ShoesSize shoesSize;
     @Enumerated(EnumType.STRING)
     Role role;
     Size size;
}
