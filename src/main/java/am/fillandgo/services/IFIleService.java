package am.fillandgo.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * The IFIleService interface provides methods for saving, downloading, deleting, and listing files.
 */
public interface IFIleService {

    /**
     * Saves a file to the specified bucket in Amazon S3.
     * @param file The file to be saved.
     * @return The generated filename of the saved file.
     * @throws IllegalArgumentException If the file is empty, the file size exceeds the maximum limit, or the file format is invalid.
     */
    String saveFile(MultipartFile file);

    /**
     * Downloads a file from Amazon S3.
     * @param fileName The name of the file to be downloaded.
     * @return The byte array representing the downloaded file.
     * @throws RuntimeException If an error occurs during the download process.
     */
    byte[] downloadFile(String fileName);

    /**
     * Deletes a file from the specified bucket in Amazon S3.
     * @param fileName The name of the file to be deleted.
     * @return The result of the delete operation.
     * @throws IllegalArgumentException If the file name is empty or null.
     * @throws RuntimeException If an error occurs during the delete operation.
     */
    String deleteFile(String fileName);

    /**
     * Retrieves a list of all file names stored in the specified bucket in Amazon S3.
     * @return A List of Strings representing the names of all files in the bucket.
     * @throws RuntimeException If an error occurs during the list operation.
     */
    List<String> listAllFiles();
}