package ai.shreds.domain.services;

import ai.shreds.domain.entities.DomainEntityMediaFile;
import ai.shreds.domain.exceptions.DomainExceptionFileTooLarge;
import ai.shreds.domain.exceptions.DomainExceptionInvalidMediaType;
import ai.shreds.domain.value_objects.DomainValueFileSize;
import ai.shreds.domain.value_objects.DomainValueFileType;

import java.util.Arrays;
import java.util.List;

public class DomainServiceMediaFile {

    private static final long MAX_FILE_SIZE = 100 * 1024 * 1024; // 100 MB
    private static final List<String> ALLOWED_MEDIA_TYPES = Arrays.asList(
            "image/jpeg",
            "image/png",
            "video/mp4"
    );

    public void validateMediaFile(DomainEntityMediaFile mediaFile) {
        validateMediaType(mediaFile.getFileType());
        validateFileSize(mediaFile.getFileSize());
    }

    public void validateMediaType(DomainValueFileType fileType) {
        if (!ALLOWED_MEDIA_TYPES.contains(fileType.getValue())) {
            throw new DomainExceptionInvalidMediaType("Invalid media type: " + fileType.getValue());
        }
    }

    public void validateFileSize(DomainValueFileSize fileSize) {
        if (fileSize.getValue() > MAX_FILE_SIZE) {
            throw a DomainExceptionFileTooLarge("File size exceeds the maximum allowed limit of " + MAX_FILE_SIZE + " bytes.");
        }
    }

    public void updateUploadStatus(DomainEntityMediaFile mediaFile, String status) {
        mediaFile.setUploadStatus(status);
    }
}