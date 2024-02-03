package peaksoft.giftlistj7g2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.giftlistj7g2.model.entities.User;

import java.util.List;
@Repository
public interface AuthRepository extends JpaRepository<User, Long> {
    @Query("select e from User e where  e.email=:email")
    List<User> findByEmail(String email);


}
