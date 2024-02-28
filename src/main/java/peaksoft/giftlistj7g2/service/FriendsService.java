package peaksoft.giftlistj7g2.service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import peaksoft.giftlistj7g2.model.dto.FriendRequest;
import peaksoft.giftlistj7g2.model.dto.FriendsResponse;
import peaksoft.giftlistj7g2.model.entities.User;
import peaksoft.giftlistj7g2.model.mapper.FriendMapper;
import peaksoft.giftlistj7g2.repository.FriendsRepository;
import peaksoft.giftlistj7g2.repository.UserRepository;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
@Service
public class FriendsService {
    private final   FriendsRepository friendsRepository;
    private final UserRepository userRepository;

    private final FriendMapper friendMapper;
    private FriendRequest friendRequest;
    public FriendsService(FriendsRepository friendsRepository, UserRepository userRepository, FriendMapper friendMapper) {
        this.friendsRepository = friendsRepository;
        this.userRepository = userRepository;
        this.friendMapper = friendMapper;
    }

    public Optional<User> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userRepository.findByEmail(login);
    }

    public FriendsResponse requestToFriend(Long friendId, Principal principal) throws UserPrincipalNotFoundException {
        User requestFriend = friendsRepository.findUser(friendId).orElseThrow(() -> new UserPrincipalNotFoundException("Not found user by this id " + friendId));
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("Not found user " + principal.getName()));

        if (user.equals(requestFriend)){
            throw new IllegalArgumentException("You cannot send a friend request to yourself");
        }
        if (user.getRequestToFriends().contains(requestFriend)){
            throw  new IllegalArgumentException("Friend request already sent to user with id: "+ friendId);
        }
          user.getRequestToFriends().add(requestFriend);
           userRepository.save(requestFriend);
          return friendMapper.mapToResponse(friendRequest);
    }

    public String acceptFriendRequest( Long friendId, Principal principal) throws UserPrincipalNotFoundException {
        User requestFriend = friendsRepository.findUser(friendId).orElseThrow(() -> new UserPrincipalNotFoundException("Not found user by this id " + friendId));
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("Not found user " + principal.getName()));

        if (user.getRequestToFriends().isEmpty()){
            throw new RuntimeException("The user did not submit a request");
        }

        user.getRequestToFriends().remove(requestFriend);
        user.getFriends().add(requestFriend);
        requestFriend.getFriends().add(user);
        return "The user successfully accept request "  ;
    }

    public String cancelRequestToFriend(Long friendId, Principal principal) throws UserPrincipalNotFoundException {
        User requestFriend = friendsRepository.findUser(friendId).orElseThrow(() -> new UserPrincipalNotFoundException("Not found user by this id " + friendId));
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("Not found user " + principal.getName()));

        user.getRequestToFriends().remove(requestFriend);
        user.getFriends().remove(requestFriend);
        return "The user canceled your request";
    }

    public String deleteRequestToFriend(Long friendId, Principal principal) throws UserPrincipalNotFoundException {
        User friend = friendsRepository.findUser(friendId).orElseThrow(() -> new UserPrincipalNotFoundException("Not found user by this id " + friendId));
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("Not found user " + principal.getName()));

        if (!user.getFriends().remove(friend)){
            throw new RuntimeException("User request not deleted");
        }
        userRepository.delete(friend);
        return "User deleted successfully";
    }

    public List<User> getAllFriends(){
       List<User> allFriends = userRepository.findAll();
       return allFriends;
    }

    public List<User> getAllRequestsFromFriends(Principal principal){
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("Not found user " + principal.getName()));
        List<User> allRequestFromFriends = user.getFriends();
        return allRequestFromFriends;
    }
}