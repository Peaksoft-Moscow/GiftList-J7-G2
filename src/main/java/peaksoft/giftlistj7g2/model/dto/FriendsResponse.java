package peaksoft.giftlistj7g2.model.dto;

import lombok.Getter;
import lombok.Setter;
import peaksoft.giftlistj7g2.model.enums.ShoesSize;
import peaksoft.giftlistj7g2.model.enums.Size;

@Getter
@Setter
public class FriendsResponse {
    private Long id;
    private String userName;
    private String lastName;
    private String email;
    private String dateOfBirth;
    private String phoneNumber;
    private String hobby;
    private String importantInfo;
    private Size size;
    private ShoesSize shoesSize;
    private String image;
}