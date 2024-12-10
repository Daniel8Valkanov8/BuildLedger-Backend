package com.buildledger.backend.service.micro;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
//import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
//import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.s3.S3Client;
//import software.amazon.awssdk.services.s3.model.PutObjectRequest;
//import software.amazon.awssdk.services.s3.model.S3Exception;
//
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileMicroService {

    // @Value("${cloud.aws.credentials.access-key}")
    // private String accessKey;

    // @Value("${cloud.aws.credentials.secret-key}")
    // private String secretKey;

    // @Value("${cloud.aws.region.static}")
    // private String region;

    // @Value("${s3.bucket-name}")
    // private String bucketName;

    // @Value("${s3.uploads-dir}")
    // private String uploadsDir;

    // private S3Client s3Client;

    // // Методът за инициализация, който създава S3 клиента след като всички @Value полета са зададени
    // @PostConstruct
    // public void initializeS3Client() {
    //     AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);
    //     this.s3Client = S3Client.builder()
    //             .region(Region.of(region))
    //             .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
    //             .build();
    // }

    //public String saveFile(MultipartFile file) {
    //    String uniqueFileName = uploadsDir + UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

    //    try {
    //        // Създаване на временен файл за качване в S3
    //        Path tempFile = Files.createTempFile("upload-", file.getOriginalFilename());
    //        file.transferTo(tempFile.toFile());

    //        // Качване на файла в S3
    //        s3Client.putObject(
    //                PutObjectRequest.builder()
    //                        .bucket(bucketName)
    //                        .key(uniqueFileName)
    //                        .build(),
    //                tempFile
    //        );

    //        // Изтриване на временния файл
    //        Files.delete(tempFile);

    //        return uniqueFileName;
    //    } catch (S3Exception | IOException e) {
    //        e.printStackTrace();
    //        throw new RuntimeException("Error uploading file to S3", e);
    //    }
    //}
    public String saveFileToContracts(MultipartFile file) {
        try {
            // Get the absolute path to the target directory
            String basePath = System.getProperty("user.dir"); // Or another known directory
            Path contractsDir = Paths.get(basePath, "src/main/resources/contracts");

            // Create the directory if it doesn't exist
            if (!Files.exists(contractsDir)) {
                Files.createDirectories(contractsDir);
            }

            // Generate a unique filename
            String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
            String uniqueFileName = UUID.randomUUID() + "_" + originalFileName;

            // Full path to the file
            Path destinationPath = contractsDir.resolve(uniqueFileName);

            // Save the file
            file.transferTo(destinationPath.toFile());

            // Return the relative path
            return "resources/contracts/" + uniqueFileName;

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving file to contracts directory", e);
        }
    }
}