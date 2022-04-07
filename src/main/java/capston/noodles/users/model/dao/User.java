package capston.noodles.users.model.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userIdx;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private char status;
    private String identification;
    private String password;
    private String phoneNumber;
    private String nickname;
    private String profileImage;
    private String token;
    private String location;
    private float latitude;
    private float longitude;
    private String description;
    private String authority;
}
