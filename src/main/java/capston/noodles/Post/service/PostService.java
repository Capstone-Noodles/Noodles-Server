package capston.noodles.Post.service;

import capston.noodles.Post.model.entity.Post;
import capston.noodles.Post.model.entity.PostImage;
import capston.noodles.Post.model.entity.dto.TotalUploadPostDto;
import capston.noodles.Post.model.entity.dto.UploadPostDto;
import capston.noodles.Post.model.response.AllPostResponse;
import capston.noodles.Post.model.response.OnePostResponse;
import capston.noodles.Post.repository.PostRepository;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private AmazonS3 s3Client;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;


    public List<AllPostResponse> getAllPostInfo(double longitude, double latitude, int userIdx) {
        return postRepository.getAllPostInfo(longitude, latitude, userIdx);
    }

    public List<OnePostResponse> getOnePostInfo(long postIdx) {
        return postRepository.getOnePostInfo(postIdx);
    }

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    public List<String> uploadImage(List<MultipartFile> imageFileList) throws IOException {
        List<String> urlList = new ArrayList<>();

        for (MultipartFile file:imageFileList) {
            String fileName = createFileName(file.getOriginalFilename());

            s3Client.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), null)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            String s = s3Client.getUrl(bucket, fileName).toString();
            urlList.add(s);
        }
        return urlList;
    }

    @Transactional
    public void postPost(UploadPostDto dto, List<MultipartFile> imageFileList) throws IOException {
        List<String> urlList = uploadImage(imageFileList);
        Post post = dto.toPost();
        postRepository.postPost(post);
        Long postIdx = post.getPostIdx();
        if (postIdx == null) {
            throw new Error();
        }

        for (String imgPath:urlList) {
            PostImage postImage = new PostImage();
            postImage.setPostIdx(postIdx);
            postImage.setImage(imgPath);
            postRepository.postImage(postImage);

            Long postImageIdx = postImage.getPostIdx();

            if (postImageIdx == null) {
                throw new Error();
            }
        }

        return;
    }

    private String createFileName(String originalFileName) {                            // 파일 이름 랜덤 생성
        return UUID.randomUUID().toString().concat(getFileExtension(originalFileName));
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public void deletePost(long postIdx) {
        postRepository.deletePost(postIdx);
    }
}
