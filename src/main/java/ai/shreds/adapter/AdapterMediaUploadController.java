package ai.shreds.adapter; 
  
 import ai.shreds.application.ports.ApplicationInputPortUploadMedia; 
 import ai.shreds.shared.SharedMediaUploadRequestParams; 
 import ai.shreds.shared.SharedMediaUploadResponseDTO; 
 import ai.shreds.application.exceptions.ApplicationExceptionValidation; 
 import ai.shreds.application.exceptions.ApplicationExceptionAuthentication; 
 import ai.shreds.application.exceptions.ApplicationExceptionAuthorization; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.PostMapping; 
 import org.springframework.web.bind.annotation.RequestHeader; 
 import org.springframework.web.bind.annotation.RequestMapping; 
 import org.springframework.web.bind.annotation.RequestParam; 
 import org.springframework.web.bind.annotation.RestController; 
 import org.springframework.web.multipart.MultipartFile; 
  
 @RestController 
 @RequestMapping("/api/media") 
 public class AdapterMediaUploadController { 
  
     private final ApplicationInputPortUploadMedia uploadMediaService; 
  
     @Autowired 
     public AdapterMediaUploadController(ApplicationInputPortUploadMedia uploadMediaService) { 
         this.uploadMediaService = uploadMediaService; 
     } 
  
     @PostMapping("/upload") 
     public ResponseEntity<SharedMediaUploadResponseDTO> uploadMedia( 
             @RequestParam("file") MultipartFile file, 
             @RequestHeader("Authorization") String authToken) { 
         try { 
             SharedMediaUploadRequestParams params = new SharedMediaUploadRequestParams(); 
             params.setFile(file); 
             params.setAuthToken(authToken); 
  
             SharedMediaUploadResponseDTO responseDTO = uploadMediaService.uploadMedia(params); 
             return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO); 
         } catch (ApplicationExceptionValidation ex) { 
             SharedMediaUploadResponseDTO errorResponse = new SharedMediaUploadResponseDTO(); 
             errorResponse.setStatus("error"); 
             errorResponse.setMessage(ex.getMessage()); 
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse); 
         } catch (ApplicationExceptionAuthentication ex) { 
             SharedMediaUploadResponseDTO errorResponse = new SharedMediaUploadResponseDTO(); 
             errorResponse.setStatus("error"); 
             errorResponse.setMessage(ex.getMessage()); 
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse); 
         } catch (ApplicationExceptionAuthorization ex) { 
             SharedMediaUploadResponseDTO errorResponse = new SharedMediaUploadResponseDTO(); 
             errorResponse.setStatus("error"); 
             errorResponse.setMessage(ex.getMessage()); 
             return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse); 
         } catch (Exception ex) { 
             SharedMediaUploadResponseDTO errorResponse = new SharedMediaUploadResponseDTO(); 
             errorResponse.setStatus("error"); 
             errorResponse.setMessage("An unexpected error occurred while uploading media. Please try again later."); 
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse); 
         } 
     } 
 } 
 