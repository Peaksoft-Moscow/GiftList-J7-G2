package peaksoft.giftlistj7g2.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.giftlistj7g2.model.dto.FriendsResponse;
import peaksoft.giftlistj7g2.model.entities.User;
import peaksoft.giftlistj7g2.service.FriendsService;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/friendship")
@RequiredArgsConstructor
public class FriendsController {
    private final FriendsService friendsService;

    @PostMapping("/send-request/{id}")
    public FriendsResponse sendRequest(@PathVariable("id") Long friendId, Principal principal) throws UserPrincipalNotFoundException {
         return friendsService.requestToFriend(friendId, principal);
    }

    @PostMapping("/acceptRequest")
    public String acceptRequest(@PathVariable("id") Long friendId, Principal principal) throws UserPrincipalNotFoundException {
        return friendsService.acceptFriendRequest( friendId, principal);
    }
    @GetMapping("/canselRequest")
    public String canselFriend(@PathVariable("id") Long friendId, Principal principal) throws UserPrincipalNotFoundException {
        String canselRequest     =  friendsService.cancelRequestToFriend(friendId, principal);
        return canselRequest;
    }

    @GetMapping("/deleteRequest")
    public String deleteFriendRequest(@PathVariable("id") Long friendId, Principal principal) throws UserPrincipalNotFoundException {
        String delete= friendsService.deleteRequestToFriend(friendId, principal);
        return  delete;
    }

    @GetMapping("/findAll")
    public List<User> findAllFriends(  ){
        List<User> allUserFriends = friendsService.getAllFriends();
        return allUserFriends;
    }
    @GetMapping("/allFriendsRequest")
    public List<User> getAllFriendRequest( Principal principal){
        List<User> allRequests = friendsService.getAllRequestsFromFriends(principal);
       return allRequests;
    }

    @GetMapping("/findFriend")
    public Optional<User> findFriendById(){
        return friendsService.getAuthenticatedUser();
    }
}