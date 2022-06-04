package capston.noodles.User.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MypageListResponse {
    private List<MypageResponse> mypageList;
    public static MypageListResponse from(List<MypageResponse> mypageList) {
        return builder()
                .mypageList(mypageList)
                .build();
    }
}
