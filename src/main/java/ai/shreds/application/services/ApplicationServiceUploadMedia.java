package ai.shreds.application.services;

import ai.shreds.application.exceptions.ApplicationExceptionAuthentication;
import ai.shreds.application.exceptions.ApplicationExceptionAuthorization;
import ai.shreds.application.exceptions.ApplicationExceptionValidation;
import ai.shreds.application.ports.ApplicationInputPortUploadMedia;
import ai.shreds.application.ports.ApplicationOutputPortAuthentication;
import ai.shreds.application.ports.ApplicationOutputPortAuthorization;
import ai.shreds.application.ports.ApplicationOutputPortMediaMetadata;
import ai.shreds.application.ports.ApplicationOutputPortTemporaryStorage;
import ai.shreds.domain.entities.DomainEntityMediaFile;
import ai.shreds.domain.exceptions.DomainExceptionFileTooLarge;
import ai.shreds.domain.exceptions.DomainExceptionInvalidMediaType;
import ai.shreds.domain.services.DomainServiceMediaFile;
import ai.shreds.domain.value_objects.DomainValueFileName;
import ai.shreds.domain.value_objects.DomainValueFileSize;
import ai.shreds.domain.value_objects.DomainValueFileType;
import ai.shreds.shared.SharedMediaMetadataDTO;
import ai.shreds.shared.SharedMediaUploadRequestParams;
import ai.shreds.shared.SharedMediaUploadResponseDTO;
import ai.shreds.shared.SharedUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
public class ApplicationServiceUploadMedia implements ApplicationInputPortUploadMedia {

    private final ApplicationOutputPortAuthentication authenticationService;
    private final ApplicationOutputPortAuthorization authorizationService;
    private final ApplicationOutputPortTemporaryStorage temporaryStorageService;
    private final ApplicationOutputPortMediaMetadata mediaMetadataService;
    private final DomainServiceMediaFile domainServiceMediaFile;

    @Autowired
    public ApplicationServiceUploadMedia(
            ApplicationOutputPortAuthentication authenticationService,
            ApplicationOutputPortAuthorization authorizationService,
            ApplicationOutputPortTemporaryStorage temporaryStorageService,
            ApplicationOutputPortMediaMetadata mediaMetadataService,
            DomainServiceMediaFile domainServiceMediaFile
    ) {
        this.authenticationService = authenticationService;
        this.authorizationService = authorizationService;
        this.temporaryStorageService = temporaryStorageService;
        this.mediaMetadataService = mediaMetadataService;
        this.domainServiceMediaFile = domainServiceMediaFile;
    }

    @Override
    public SharedMediaUploadResponseDTO uploadMedia(SharedMediaUploadRequestParams params) throws ApplicationExceptionAuthentication, ApplicationExceptionAuthorization, ApplicationExceptionValidation {

        MultipartFile file = params.getFile();
        String authToken = params.getAuthToken();

        if (file == null || file.isEmpty()) {
            throw new ApplicationExceptionValidation("Media file must not be null or empty.", 400);
        }

        if (authToken == null || authToken.isEmpty()) {
            throw new ApplicationExceptionAuthentication("Authentication token must not be null or empty.", 401);
        }

        SharedUserDTO user = authenticationService.validateAuthToken(authToken);

        boolean hasPermission = authorizationService.checkUploadPermission(user.getUserId());

        if (!hasPermission) {
            throw new ApplicationExceptionAuthorization("You do not have permission to upload media.", 403);
        }

        DomainValueFileName fileName = new DomainValueFileName(file.getOriginalFilename());
        DomainValueFileType fileType = new DomainValueFileType(file.getContentType());
        DomainValueFileSize fileSize = new DomainValueFileSize(file.getSize());

        DomainEntityMediaFile mediaFile = new DomainEntityMediaFile();

        mediaFile.setFileName(fileName);
        mediaFile.setFileType(fileType);
        mediaFile.setFileSize(fileSize);
        mediaFile.setUploadTimestamp(LocalDateTime.now()); // Fixed to use LocalDateTime
        mediaFile.setUserId(user.getUserId());
        mediaFile.setUploadStatus("pending");

        try {
            domainServiceMediaFile.validateMediaFile(mediaFile);
        } catch (DomainExceptionInvalidMediaType | DomainExceptionFileTooLarge e) {
            throw new ApplicationExceptionValidation(e.getMessage(), 400);
        }

        String temporaryStoragePath = temporaryStorageService.storeFileTemporarily(file);

        mediaFile.setTemporaryStoragePath(temporaryStoragePath);

        SharedMediaMetadataDTO metadataDTO = new SharedMediaMetadataDTO();

        metadataDTO.setFileName(fileName.getValue());
        metadataDTO.setFileType(fileType.getValue());
        metadataDTO.setFileSize(fileSize.getValue());
        metadataDTO.setUploadTimestamp(mediaFile.getUploadTimestamp());
        metadataDTO.setUserId(mediaFile.getUserId());
        metadataDTO.setTemporaryStoragePath(temporaryStoragePath);

        String temporaryMediaId = mediaMetadataService.createMediaMetadata(metadataDTO);

        mediaFile.setTemporaryMediaId(temporaryMediaId);

        SharedMediaUploadResponseDTO responseDTO = new SharedMediaUploadResponseDTO();

        responseDTO.setStatus("success");
        responseDTO.setMessage("Media uploaded successfully.");
        responseDTO.setTemporaryMediaId(temporaryMediaId);
        responseDTO.setUploadStatus(mediaFile.getUploadStatus());

        return responseDTO;
    }
}