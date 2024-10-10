package ai.shreds.application.services; 
  
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
  
 import ai.shreds.application.exceptions.ApplicationExceptionAuthentication; 
 import ai.shreds.application.ports.ApplicationOutputPortAuthentication; 
 import ai.shreds.shared.dto.SharedUserDTO; 
 import ai.shreds.infrastructure.external_services.AuthenticationServiceClient; 
  
 @Service 
 public class ApplicationServiceAuthentication implements ApplicationOutputPortAuthentication { 
  
     private final AuthenticationServiceClient authenticationServiceClient; 
  
     @Autowired 
     public ApplicationServiceAuthentication(AuthenticationServiceClient authenticationServiceClient) { 
         this.authenticationServiceClient = authenticationServiceClient; 
     } 
  
     @Override 
     public SharedUserDTO validateAuthToken(String token) { 
         try { 
             // Validate the authentication token using the external Authentication Service 
             SharedUserDTO user = authenticationServiceClient.validateAuthToken(token); 
  
             if (user == null) { 
                 // Token is invalid or expired 
                 throw new ApplicationExceptionAuthentication("Authentication failed. Invalid or expired token.", 401); 
             } 
  
             return user; 
         } catch (ApplicationExceptionAuthentication e) { 
             // Rethrow custom authentication exceptions 
             throw e; 
         } catch (Exception e) { 
             // Handle other exceptions 
             throw new ApplicationExceptionAuthentication("Authentication service error.", 500, e); 
         } 
     } 
 } 
 