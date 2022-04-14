package capston.noodles.Post.service;

import capston.noodles.Post.model.response.AllPostResponse;
import capston.noodles.Post.model.response.OnePostResponse;
import capston.noodles.Post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.InputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UploadService s3Service;

    public List<AllPostResponse> getAllPostInfo(double longitude, double latitude) {
        return postRepository.getAllPostInfo(longitude, latitude);
    }

    public List<OnePostResponse> getOnePostInfo(long postIdx) {
        return postRepository.getOnePostInfo(postIdx);
    }

    public String uploadImage(MultipartFile file) {
        String fileName = createFileName(file.getOriginalFilename());   // 원래의 파일이름 가져오기(getOriginalFilename 얘는 내장함수인듯)
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());
        try(InputStream inputStream = file.getInputStream()) {
            s3Service.uploadFile(inputStream, objectMetadata, fileName);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("파일 변환 중 에러가 발생했습니다. (%s)", file.getOriginalFilename()));
        }
        return s3Service.getFileUrl(fileName);
    }

    private String createFileName(String originalFileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(originalFileName));
    }

    private String getFileExtension(String fileName) {
        try{
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format("잘못된 형식의 파일(%s)입니다.", fileName));
        }
    }
}
