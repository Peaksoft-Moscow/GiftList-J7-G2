package peaksoft.giftlistj7g2.model.mapper;

import org.springframework.stereotype.Component;
import peaksoft.giftlistj7g2.model.dto.FriendRequest;
import peaksoft.giftlistj7g2.model.dto.FriendsResponse;

@Component
public class FriendMapper {
    public FriendsResponse mapToResponse(FriendRequest friendRequest) {
        FriendsResponse friendsResponse = new FriendsResponse();
        friendsResponse.setId(friendRequest.getId());
        friendsResponse.setUserName(friendRequest.getUserName());
        friendsResponse.setLastName(friendRequest.getLastName());
        friendsResponse.setEmail(friendRequest.getEmail());
        friendsResponse.setDateOfBirth(friendRequest.getDateOfBirth());
        friendsResponse.setPhoneNumber(friendRequest.getPhoneNumber());
        friendsResponse.setHobby(friendRequest.getHobby());
        friendsResponse.setImportantInfo(friendRequest.getImportantInfo());
        friendsResponse.setSize(friendRequest.getSize());
        friendsResponse.setShoesSize(friendRequest.getShoesSize());
        friendsResponse.setImage(friendRequest.getImage());
        return  friendsResponse;
    }
}