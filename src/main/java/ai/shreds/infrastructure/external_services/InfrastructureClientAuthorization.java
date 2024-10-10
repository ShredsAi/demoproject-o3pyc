package ai.shreds.infrastructure.external_services; 
  
 import ai.shreds.application.ports.ApplicationOutputPortAuthorization; 
 import ai.shreds.infrastructure.exceptions.InfrastructureExceptionNetworkError; 
 import ai.shreds.infrastructure.external_services.clients.AuthorizationServiceFeignClient; 
 import ai.shreds.infrastructure.external_services.dto.CheckPermissionRequest; 
 import ai.shreds.infrastructure.external_services.dto.CheckPermissionResponse; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
 import feign.FeignException; 
  
 @Service 
 public class InfrastructureClientAuthorization implements ApplicationOutputPortAuthorization { 
  
     @Autowired 
     private AuthorizationServiceFeignClient authorizationServiceFeignClient; 
  
     @Override 
     public Boolean checkUploadPermission(String userId) { 
         CheckPermissionRequest request = new CheckPermissionRequest(userId, "UPLOAD_MEDIA"); 
  
         try { 
             CheckPermissionResponse response = authorizationServiceFeignClient.checkPermission(request); 
             return response.isAuthorized(); 
         } catch (FeignException e) { 
             throw new InfrastructureExceptionNetworkError("Error communicating with Authorization Service", e); 
         } 
     } 
 } 
 