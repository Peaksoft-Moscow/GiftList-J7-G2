package peaksoft.giftlistj7g2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.giftlistj7g2.model.entities.User;

import java.util.Optional;

public interface FriendsRepository extends JpaRepository<User, Long> {
    @Query("select user from User  user where user.id =: id")
   Optional<User>  findUser(@Param("id") Long id);

    @Query("select friend from User user join user.friends friend where friend.id =: friendId and  user.id =: userId")
    Optional<User> findFriendsById(@Param("friendId") Long friendId, @Param("userId") Optional<User> userId);
}