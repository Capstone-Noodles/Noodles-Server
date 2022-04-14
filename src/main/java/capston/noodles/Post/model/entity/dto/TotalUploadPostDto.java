package capston.noodles.Post.model.entity.dto;

import lombok.Data;

@Data
public class TotalUploadPostDto extends UploadPostDto {
    private String image;

    static public TotalUploadPostDto toTotalUploadPostDto(UploadPostDto dto, String image) {
        TotalUploadPostDto totalUploadPostDto = new TotalUploadPostDto();
        totalUploadPostDto.setImage(image);
        return totalUploadPostDto;
    }

}
