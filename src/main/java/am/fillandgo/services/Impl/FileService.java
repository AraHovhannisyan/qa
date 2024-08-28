package am.fillandgo.services.Impl;

import am.fillandgo.services.IFIleService;
import am.fillandgo.util.generators.RandomStringGeneratorUtil;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The FileService class provides methods to interact with files stored in Amazon S3.
 */
@Service
@RequiredArgsConstructor
public class FileService implements IFIleService {


    private final AmazonS3 s3;

    @Value("${bucketNAme}")
    private String bucketName;


    /**
     * Saves a file to the specified bucket in Amazon S3.
     * @param file The file to be saved.
     * @return The generated filename of the saved file.
     * @throws ResourceAccessException If the file is empty.
     */
    @Override
    public String saveFile(MultipartFile file) {

        if (!file.isEmpty()) {
            String newFilename = null;
            String originalFilename = file.getOriginalFilename();
            if (originalFilename != null && !originalFilename.isEmpty()) {
                int lastIndexOfDot = originalFilename.lastIndexOf('.');
                if (lastIndexOfDot == -1) {
                    newFilename = RandomStringGeneratorUtil.uuId();
                } else {
                    String ext = originalFilename.substring(lastIndexOfDot + 1);
                    newFilename = String.format("%s.%s", RandomStringGeneratorUtil.uuId(), ext);

                }
                try (InputStream inputStream = file.getInputStream()) {
                    ObjectMetadata metadata = new ObjectMetadata();
                    metadata.setContentLength(file.getSize());
                    s3.putObject(bucketName, newFilename, inputStream, metadata);
                    return newFilename;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return newFilename;
        }
        throw new ResourceAccessException("File is empty");
    }

    /**
     * Downloads a file from Amazon S3.
     * @param fileName The name of the file to be downloaded.
     * @return The byte array representing the downloaded file.
     * @throws RuntimeException If an error occurs during the download process.
     */
    @Override
    public byte[] downloadFile(String fileName) {
        S3Object object = s3.getObject(bucketName, fileName);
        S3ObjectInputStream objectContent = object.getObjectContent();
        try {
            return IOUtils.toByteArray(objectContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes a file from the specified bucket in Amazon S3.
     * @param fileName The name of the file to be deleted.
     * @return A string indicating that the file has been deleted.
     */
    @Override
    public String deleteFile(String fileName) {
        s3.deleteObject(bucketName, fileName);
        return "deleted";
    }

    /**
     * Retrieves a list of all files stored in the specified bucket in Amazon S3.
     * @return A list of file names.
     */
    @Override
    public List<String> listAllFiles() {
        ListObjectsV2Result listObjectsV2Result = s3.listObjectsV2(bucketName);
        return listObjectsV2Result.getObjectSummaries().stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());
    }
}