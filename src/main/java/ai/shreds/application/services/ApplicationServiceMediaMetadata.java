package ai.shreds.application.services; 
  
 import ai.shreds.application.exceptions.ApplicationExceptionValidation; 
 import ai.shreds.application.ports.ApplicationOutputPortMediaMetadata; 
 import ai.shreds.domain.entities.DomainEntityMediaFile; 
 import ai.shreds.domain.ports.DomainPortMediaRepository; 
 import ai.shreds.shared.SharedMediaMetadataDTO; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
  
 import java.util.UUID; 
  
 @Service 
 public class ApplicationServiceMediaMetadata implements ApplicationOutputPortMediaMetadata { 
  
     private final DomainPortMediaRepository mediaRepository; 
  
     @Autowired 
     public ApplicationServiceMediaMetadata(DomainPortMediaRepository mediaRepository) { 
         this.mediaRepository = mediaRepository; 
     } 
  
     @Override 
     public String createMediaMetadata(SharedMediaMetadataDTO metadata) { 
         // Generate unique temporaryMediaId if not present 
         String temporaryMediaId = UUID.randomUUID().toString(); 
  
         // Map SharedMediaMetadataDTO to DomainEntityMediaFile manually 
         DomainEntityMediaFile mediaFile = new DomainEntityMediaFile(); 
         mediaFile.setTemporaryMediaId(temporaryMediaId); 
         mediaFile.setFileName(metadata.getFileName()); 
         mediaFile.setFileType(metadata.getFileType()); 
         mediaFile.setFileSize(metadata.getFileSize()); 
         mediaFile.setUploadTimestamp(metadata.getUploadTimestamp()); 
         mediaFile.setUserId(metadata.getUserId()); 
         mediaFile.setTemporaryStoragePath(metadata.getTemporaryStoragePath()); 
         mediaFile.setUploadStatus("pending"); 
  
         // Save mediaFile to repository 
         mediaRepository.save(mediaFile); 
  
         // Return the temporaryMediaId 
         return temporaryMediaId; 
     } 
  
     @Override 
     public void updateUploadStatus(String temporaryMediaId, String status) { 
         // Retrieve the media file 
         DomainEntityMediaFile mediaFile = mediaRepository.findByTemporaryMediaId(temporaryMediaId); 
         if (mediaFile == null) { 
             throw new ApplicationExceptionValidation("Media file not found for temporaryMediaId: " + temporaryMediaId); 
         } 
  
         // Update the upload status 
         mediaFile.setUploadStatus(status); 
  
         // Save the updated media file 
         mediaRepository.save(mediaFile); 
     } 
 }