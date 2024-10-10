package ai.shreds.domain.entities; 
  
 import ai.shreds.domain.value_objects.DomainValueFileName; 
 import ai.shreds.domain.value_objects.DomainValueFileType; 
 import ai.shreds.domain.value_objects.DomainValueFileSize; 
  
 import java.time.LocalDateTime; 
  
 public class DomainEntityMediaFile { 
     private String temporaryMediaId; 
     private DomainValueFileName fileName; 
     private DomainValueFileType fileType; 
     private DomainValueFileSize fileSize; 
     private LocalDateTime uploadTimestamp; 
     private String uploadStatus; 
     private String userId; 
     private String temporaryStoragePath; 
  
     public DomainEntityMediaFile() { 
     } 
  
     public DomainEntityMediaFile(String temporaryMediaId, DomainValueFileName fileName, DomainValueFileType fileType, 
                                  DomainValueFileSize fileSize, LocalDateTime uploadTimestamp, String uploadStatus, 
                                  String userId, String temporaryStoragePath) { 
         this.temporaryMediaId = temporaryMediaId; 
         this.fileName = fileName; 
         this.fileType = fileType; 
         this.fileSize = fileSize; 
         this.uploadTimestamp = uploadTimestamp; 
         this.uploadStatus = uploadStatus; 
         this.userId = userId; 
         this.temporaryStoragePath = temporaryStoragePath; 
     } 
  
     public String getTemporaryMediaId() { 
         return temporaryMediaId; 
     } 
  
     public void setTemporaryMediaId(String temporaryMediaId) { 
         this.temporaryMediaId = temporaryMediaId; 
     } 
  
     public DomainValueFileName getFileName() { 
         return fileName; 
     } 
  
     public void setFileName(DomainValueFileName fileName) { 
         this.fileName = fileName; 
     } 
  
     public DomainValueFileType getFileType() { 
         return fileType; 
     } 
  
     public void setFileType(DomainValueFileType fileType) { 
         this.fileType = fileType; 
     } 
  
     public DomainValueFileSize getFileSize() { 
         return fileSize; 
     } 
  
     public void setFileSize(DomainValueFileSize fileSize) { 
         this.fileSize = fileSize; 
     } 
  
     public LocalDateTime getUploadTimestamp() { 
         return uploadTimestamp; 
     } 
  
     public void setUploadTimestamp(LocalDateTime uploadTimestamp) { 
         this.uploadTimestamp = uploadTimestamp; 
     } 
  
     public String getUploadStatus() { 
         return uploadStatus; 
     } 
  
     public void setUploadStatus(String uploadStatus) { 
         this.uploadStatus = uploadStatus; 
     } 
  
     public String getUserId() { 
         return userId; 
     } 
  
     public void setUserId(String userId) { 
         this.userId = userId; 
     } 
  
     public String getTemporaryStoragePath() { 
         return temporaryStoragePath; 
     } 
  
     public void setTemporaryStoragePath(String temporaryStoragePath) { 
         this.temporaryStoragePath = temporaryStoragePath; 
     } 
 } 
 