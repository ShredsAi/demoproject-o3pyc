package ai.shreds.application.services; 
  
 import ai.shreds.application.ports.ApplicationOutputPortTemporaryStorage; 
 import org.springframework.beans.factory.annotation.Value; 
 import org.springframework.stereotype.Service; 
 import org.springframework.web.multipart.MultipartFile; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 import java.io.File; 
 import java.io.IOException; 
 import java.nio.file.Files; 
 import java.nio.file.Path; 
 import java.nio.file.Paths; 
 import java.util.UUID; 
  
 @Service 
 public class ApplicationServiceTemporaryStorage implements ApplicationOutputPortTemporaryStorage { 
  
     private static final Logger logger = LoggerFactory.getLogger(ApplicationServiceTemporaryStorage.class); 
  
     @Value("${temporary.storage.location}") 
     private String temporaryStorageLocation; 
  
     @Override 
     public String storeFileTemporarily(MultipartFile file) { 
         try { 
             // Generate a unique filename with original extension 
             String originalFilename = file.getOriginalFilename(); 
             String fileExtension = ""; 
             if (originalFilename != null && originalFilename.contains(".")) { 
                 fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".")); 
             } 
             String uniqueFilename = UUID.randomUUID().toString() + fileExtension; 
  
             // Ensure the temporary storage directory exists 
             Path storageDir = Paths.get(temporaryStorageLocation); 
             if (!Files.exists(storageDir)) { 
                 Files.createDirectories(storageDir); 
             } 
  
             // Define the full path for the stored file 
             Path storagePath = storageDir.resolve(uniqueFilename); 
  
             // Save the file to the temporary storage location 
             file.transferTo(storagePath.toFile()); 
  
             // Return the full storage path as a String 
             return storagePath.toString(); 
         } catch (IOException e) { 
             logger.error("Failed to store file temporarily", e); 
             throw new RuntimeException("Failed to store file temporarily", e); 
         } 
     } 
 } 
 