package capston.noodles.Post.model.entity.dto;

import lombok.Data;

@Data
public class SearchPostByHashtagDto extends LocationDto{
    private String hashtag;

    public String toString(){
        return "hashtag=" + hashtag + "\n"
                +"longitude" + getLongitude() + "\n"
                +"latitude" + getLatitude() + "\n";
    }
}
