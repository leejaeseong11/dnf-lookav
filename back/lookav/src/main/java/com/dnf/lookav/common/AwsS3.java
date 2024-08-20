package com.dnf.lookav.common;

import static com.dnf.lookav.avatar.exception.ErrorCode.*;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.dnf.lookav.avatar.exception.MyException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;

@Component
public class AwsS3 {
    private AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public static final String DNF_IMAGE_LINK = "https://img-api.neople.co.kr/df/servers/";

    public AwsS3(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public void uploadImage(String characterId, String serverId) {
        try {
            URL url = new URL(DNF_IMAGE_LINK + serverId + "/characters/" + characterId + "?zoom=1");
            try (InputStream in = url.openStream()) {
                if (amazonS3 != null) {
                    PutObjectRequest putObjectRequest =
                            new PutObjectRequest(
                                    bucketName, characterId + ".jpg", convertInputStreamToFile(in));
                    putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);

                    ObjectMetadata metadata = new ObjectMetadata();
                    metadata.setContentType("image/jpeg");
                    putObjectRequest.setMetadata(metadata);

                    amazonS3.putObject(putObjectRequest);
                    amazonS3 = null;
                }
                in.close();
            } catch (IOException e) {
                throw new MyException(AWS_SERVER_ERROR);
            }
        } catch (IOException e) {
            throw new MyException(MALFORMED_URL);
        }
    }

    private static File convertInputStreamToFile(InputStream inputStream) {
        File tempFile = null;
        try {
            tempFile = File.createTempFile(String.valueOf(inputStream.hashCode()), ".tmp");
        } catch (IOException e) {
            throw new MyException(AWS_SERVER_ERROR);
        }
        tempFile.deleteOnExit();

        copyInputStreamToFile(inputStream, tempFile);

        return tempFile;
    }

    private static void copyInputStreamToFile(InputStream inputStream, File file) {
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            int read;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            throw new MyException(AWS_SERVER_ERROR);
        }
    }
}
