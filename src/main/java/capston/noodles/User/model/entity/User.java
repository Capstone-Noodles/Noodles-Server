package capston.noodles.User.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int userIdx;
    private Date createdAt;
    private Date updatedAt;
    private String status;
    private String identification;
    private String phoneNumber;
    private String nickname;
    private String profileImage;
    private String token;
    private String location;
    private double latitude;
    private double longitude;
    private String description;
}
