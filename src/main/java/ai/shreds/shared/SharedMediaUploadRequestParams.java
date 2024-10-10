package ai.shreds.shared; 
  
 import org.springframework.web.multipart.MultipartFile; 
 import javax.validation.constraints.NotNull; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import lombok.AllArgsConstructor; 
  
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class SharedMediaUploadRequestParams { 
  
     @NotNull(message = "File must not be null") 
     private MultipartFile file; 
  
     @NotNull(message = "AuthToken must not be null") 
     private String authToken; 
 }