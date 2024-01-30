package peaksoft.giftlistj7g2.model.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
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
     @Enumerated(EnumType.STRING)
     Size size;
     @Enumerated(EnumType.ORDINAL)
     ShoesSize shoesSize;
     @OneToMany
     Notification notification;
     Holiday holiday;
     Complaint complaint;
     @OneToMany
     List<Gift> gifts;

}
