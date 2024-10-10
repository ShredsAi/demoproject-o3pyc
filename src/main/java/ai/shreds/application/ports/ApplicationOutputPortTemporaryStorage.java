package ai.shreds.application.ports;

import org.springframework.web.multipart.MultipartFile;

/**
 * Defines the contract for storing media files temporarily.
 * This is an output port in the application layer, implemented by the infrastructure layer.
 */
public interface ApplicationOutputPortTemporaryStorage {
    /**
     * Stores the given media file in a temporary storage location.
     *
     * @param file the media file to be stored temporarily
     * @return the path to the temporary storage location of the media file
     */
    String storeFileTemporarily(MultipartFile file);
}