package ai.shreds.infrastructure.repositories; 
  
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Repository; 
 import org.springframework.data.mongodb.repository.MongoRepository; 
 import org.springframework.data.mongodb.core.mapping.Document; 
 import org.springframework.data.annotation.Id; 
 import java.util.Date; 
 import ai.shreds.domain.entities.DomainEntityMediaFile; 
 import ai.shreds.domain.value_objects.DomainValueFileName; 
 import ai.shreds.domain.value_objects.DomainValueFileType; 
 import ai.shreds.domain.value_objects.DomainValueFileSize; 
 import ai.shreds.domain.ports.DomainPortMediaRepository; 
 import ai.shreds.infrastructure.exceptions.InfrastructureExceptionDataAccess; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import lombok.AllArgsConstructor; 
  
 @Repository 
 public class InfrastructureRepositoryImplMedia implements DomainPortMediaRepository { 
  
     @Autowired 
     private MediaFileMongoRepository mediaFileMongoRepository; 
  
     @Override 
     public void save(DomainEntityMediaFile mediaFile) { 
         try { 
             MediaFileDocument document = mapToDocument(mediaFile); 
             mediaFileMongoRepository.save(document); 
         } catch (Exception e) { 
             throw new InfrastructureExceptionDataAccess("Failed to save media file", e); 
         } 
     } 
  
     @Override 
     public DomainEntityMediaFile findByTemporaryMediaId(String temporaryMediaId) { 
         try { 
             MediaFileDocument document = mediaFileMongoRepository.findByTemporaryMediaId(temporaryMediaId); 
             if (document != null) { 
                 return mapToDomainEntity(document); 
             } else { 
                 return null; 
             } 
         } catch (Exception e) { 
             throw new InfrastructureExceptionDataAccess("Failed to find media file by temporaryMediaId", e); 
         } 
     } 
  
     private MediaFileDocument mapToDocument(DomainEntityMediaFile mediaFile) { 
         return new MediaFileDocument( 
                 mediaFile.getTemporaryMediaId(), 
                 mediaFile.getFileName().getValue(), 
                 mediaFile.getFileType().getValue(), 
                 mediaFile.getFileSize().getValue(), 
                 mediaFile.getUploadTimestamp(), 
                 mediaFile.getUploadStatus(), 
                 mediaFile.getUserId(), 
                 mediaFile.getTemporaryStoragePath() 
         ); 
     } 
  
     private DomainEntityMediaFile mapToDomainEntity(MediaFileDocument document) { 
         return new DomainEntityMediaFile( 
                 document.getTemporaryMediaId(), 
                 new DomainValueFileName(document.getFileName()), 
                 new DomainValueFileType(document.getFileType()), 
                 new DomainValueFileSize(document.getFileSize()), 
                 document.getUploadTimestamp(), 
                 document.getUploadStatus(), 
                 document.getUserId(), 
                 document.getTemporaryStoragePath() 
         ); 
     } 
 } 
  
 @Document(collection = "MediaFiles") 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 class MediaFileDocument { 
  
     @Id 
     private String temporaryMediaId; 
     private String fileName; 
     private String fileType; 
     private Long fileSize; 
     private Date uploadTimestamp; 
     private String uploadStatus; 
     private String userId; 
     private String temporaryStoragePath; 
  
 } 
  
 interface MediaFileMongoRepository extends MongoRepository<MediaFileDocument, String> { 
     MediaFileDocument findByTemporaryMediaId(String temporaryMediaId); 
 } 
 