package ai.shreds.infrastructure.exceptions; 
  
 public class InfrastructureExceptionNetworkError extends RuntimeException { 
      
     public InfrastructureExceptionNetworkError() { 
         super(); 
     } 
      
     public InfrastructureExceptionNetworkError(String message) { 
         super(message); 
     } 
      
     public InfrastructureExceptionNetworkError(String message, Throwable cause) { 
         super(message, cause); 
     } 
      
     public InfrastructureExceptionNetworkError(Throwable cause) { 
         super(cause); 
     } 
      
     @Override 
     public String getMessage() { 
         return super.getMessage(); 
     } 
 } 
 