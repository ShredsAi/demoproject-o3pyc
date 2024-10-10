package ai.shreds.application.ports; 
  
 /** 
  * ApplicationOutputPortAuthorization is an outbound port in the Hexagonal Architecture. 
  * It provides an interface for checking user permissions with the Authorization Service. 
  */ 
 public interface ApplicationOutputPortAuthorization { 
  
     /** 
      * Verifies that the user with the given userId has permission to upload media. 
      * 
      * @param userId the identifier of the user whose permissions are being checked 
      * @return true if the user has upload permission, false otherwise 
      */ 
     Boolean checkUploadPermission(String userId); 
 }