package umc.spring.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import umc.spring.config.AmazonConfig;
import umc.spring.domain.Uuid;
import umc.spring.repository.UuidRepository.UuidRepository;

import javax.xml.crypto.dsig.keyinfo.KeyName;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AmazonS3Manager {
    private final AmazonS3 amazonS3;

    private final AmazonConfig amazonConfig;

    private final UuidRepository uuidRepository;

    public String uploadFile(String KeyName, MultipartFile file) throws IOException {
        //return null;
        System.out.println(KeyName);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        try {
            amazonS3.putObject(new PutObjectRequest(amazonConfig.getBucket(), KeyName, file.getInputStream(), metadata));
        } catch (IOException e){
            log.error("error at AmazonS3Manager uploadFile : {}", (Object) e.getStackTrace());
        }

        return amazonS3.getUrl(amazonConfig.getBucket(), KeyName).toString();
    }

    public String generateReviewKeyName(Uuid uuid) {
        return amazonConfig.getReviewPath() + '/' + uuid.getUuid();
    }
}
