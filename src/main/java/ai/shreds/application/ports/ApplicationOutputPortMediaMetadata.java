package ai.shreds.application.ports;

import ai.shreds.shared.SharedMediaMetadataDTO;

/**
 * ApplicationOutputPortMediaMetadata defines the methods for interacting with media metadata.
 * It serves as an output port for the application layer to communicate with the infrastructure layer.
 */
public interface ApplicationOutputPortMediaMetadata {
    /**
     * Creates a new media metadata entry in the database with the provided details.
     * Returns the generated temporaryMediaId associated with the uploaded media file.
     *
     * @param metadata the media metadata details
     * @return the temporaryMediaId of the media file
     */
    String createMediaMetadata(SharedMediaMetadataDTO metadata);

    /**
     * Updates the upload status of a media file identified by the temporaryMediaId.
     *
     * @param temporaryMediaId the unique identifier of the media file
     * @param status the new upload status to be set
     */
    void updateUploadStatus(String temporaryMediaId, String status);
}