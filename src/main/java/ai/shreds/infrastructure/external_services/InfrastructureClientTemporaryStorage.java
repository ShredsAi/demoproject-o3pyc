package ai.shreds.infrastructure.external_services; 
  
 import ai.shreds.application.ports.ApplicationOutputPortTemporaryStorage; 
 import org.springframework.beans.factory.annotation.Value; 
 import org.springframework.stereotype.Service; 
 import org.springframework.web.multipart.MultipartFile; 
  
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import java.io.IOException; 
 import java.nio.file.*; 
 import java.util.UUID; 
  
 @Service 
 public class InfrastructureClientTemporaryStorage implements ApplicationOutputPortTemporaryStorage { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureClientTemporaryStorage.class); 
  
     @Value('${temp.storage.directory}') 
     private String tempStorageDir; 
  
     @Override 
     public String storeFileTemporarily(MultipartFile file) { 
         try { 
             // Extract original filename and extension 
             String originalFilename = file.getOriginalFilename(); 
             String extension = ''; 
             if (originalFilename != null && originalFilename.lastIndexOf('.') != -1) { 
                 extension = originalFilename.substring(originalFilename.lastIndexOf('.')); 
             } 
  
             // Generate unique filename 
             String uniqueFilename = UUID.randomUUID().toString() + extension; 
  
             // Determine storage path 
             Path storageDirectory = Paths.get(tempStorageDir); 
             if (!Files.exists(storageDirectory)) { 
                 Files.createDirectories(storageDirectory); 
             } 
  
             Path destinationFile = storageDirectory.resolve(uniqueFilename); 
  
             // Save the file using InputStream to handle large files efficiently 
             try (var inputStream = file.getInputStream()) { 
                 Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING); 
             } 
  
             // Return the storage path as String 
             return destinationFile.toAbsolutePath().toString(); 
  
         } catch (IOException e) { 
             // Handle exception 
             logger.error('Failed to store file temporarily', e); 
             throw new RuntimeException('Failed to store file temporarily', e); 
         } 
     } 
 } 
 