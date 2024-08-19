package com.dnf.lookav.common;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;

@Component
public class AwsS3 {
    private AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public AwsS3(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public void uploadImage(String characterId, String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            try (InputStream in = url.openStream()) {
                if (amazonS3 != null) {
                    try {
                        PutObjectRequest putObjectRequest =
                                new PutObjectRequest(
                                        bucketName,
                                        characterId + ".jpg",
                                        convertInputStreamToFile(in));
                        putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);

                        ObjectMetadata metadata = new ObjectMetadata();
                        metadata.setContentType("image/jpeg");
                        putObjectRequest.setMetadata(metadata);

                        amazonS3.putObject(putObjectRequest);
                    } catch (AmazonServiceException e) {
                        throw new RuntimeException(e);
                    } finally {
                        amazonS3 = null;
                    }
                }
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File convertInputStreamToFile(InputStream inputStream) {
        File tempFile = null;
        try {
            tempFile = File.createTempFile(String.valueOf(inputStream.hashCode()), ".tmp");
        } catch (IOException e) {
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
    }
}
