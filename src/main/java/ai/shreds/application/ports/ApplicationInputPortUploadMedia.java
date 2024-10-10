package ai.shreds.application.ports; 
  
 import ai.shreds.shared.SharedMediaUploadRequestParams; 
 import ai.shreds.shared.SharedMediaUploadResponseDTO; 
  
 public interface ApplicationInputPortUploadMedia { 
  
     SharedMediaUploadResponseDTO uploadMedia(SharedMediaUploadRequestParams params); 
  
 }