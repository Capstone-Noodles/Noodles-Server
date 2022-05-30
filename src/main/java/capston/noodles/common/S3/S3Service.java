/**
 * 2020-05-30 최승용
 * deleteImage 수정해야함
 * s3에서 access denied (참고: https://bakjuna.tistory.com/99)
 */

package capston.noodles.common.S3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
public class S3Service {
    private AmazonS3 s3Client;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @PostConstruct
    public void setS3Client(){
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }


    public String uploadImage(MultipartFile file){
        String fileName = createFileName(file.getOriginalFilename());

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        try {
            s3Client.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            throw new S3Exception("이미지 등록중 에러가 발생하였습니다.", e.getCause());

        }
        String s = s3Client.getUrl(bucket, fileName).toString();
        return s;
    }


    public void deleteImage(String imageName) {
        String key = imageName.substring(48);
        System.out.println("key = " + key);
        try {
            s3Client.deleteObject(bucket, key);
        } catch (AmazonServiceException e) {
//            throw new S3Exception("삭제 중 에러가 발생하였습니다.", e.getCause());
            throw new S3Exception(e.getMessage());
        }
    }

    private String createFileName(String originalFileName) {                            // 파일 이름 랜덤 생성
        return UUID.randomUUID().toString().concat(getFileExtension(originalFileName));
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }



}
