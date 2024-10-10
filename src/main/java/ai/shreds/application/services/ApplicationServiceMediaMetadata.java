package ai.shreds.application.services;

import ai.shreds.application.exceptions.ApplicationExceptionValidation;
import ai.shreds.application.ports.ApplicationOutputPortMediaMetadata;
import ai.shreds.domain.entities.DomainEntityMediaFile;
import ai.shreds.domain.ports.DomainPortMediaRepository;
import ai.shreds.domain.value_objects.DomainValueFileName;
import ai.shreds.domain.value_objects.DomainValueFileType;
import ai.shreds.domain.value_objects.DomainValueFileSize;
import ai.shreds.shared.SharedMediaMetadataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplicationServiceMediaMetadata implements ApplicationOutputPortMediaMetadata {

    private final DomainPortMediaRepository mediaRepository;

    @Override
    public String createMediaMetadata(SharedMediaMetadataDTO metadata) {
        String temporaryMediaId = metadata.getTemporaryMediaId() != null ? metadata.getTemporaryMediaId() : UUID.randomUUID().toString();

        DomainEntityMediaFile mediaFile = new DomainEntityMediaFile();
        mediaFile.setTemporaryMediaId(temporaryMediaId);
        mediaFile.setFileName(new DomainValueFileName(metadata.getFileName()));
        mediaFile.setFileType(new DomainValueFileType(metadata.getFileType()));
        mediaFile.setFileSize(new DomainValueFileSize(metadata.getFileSize()));
        mediaFile.setUploadTimestamp(metadata.getUploadTimestamp());
        mediaFile.setUserId(metadata.getUserId());
        mediaFile.setTemporaryStoragePath(metadata.getTemporaryStoragePath());
        mediaFile.setUploadStatus("pending");

        mediaRepository.save(mediaFile);

        return temporaryMediaId;
    }

    @Override
    public void updateUploadStatus(String temporaryMediaId, String status) {
        DomainEntityMediaFile mediaFile = mediaRepository.findByTemporaryMediaId(temporaryMediaId);
        if (mediaFile == null) {
            throw new ApplicationExceptionValidation("Media file not found for temporaryMediaId: " + temporaryMediaId, 404);
        }

        mediaFile.setUploadStatus(status);
        mediaRepository.save(mediaFile);
    }
}
