package ai.shreds.shared; 
  
 import lombok.Getter; 
  
 @Getter 
 public class SharedExceptionAuthorization extends RuntimeException { 
  
     private static final long serialVersionUID = 1L; 
  
     private final String message; 
     private final int errorCode; 
  
     public SharedExceptionAuthorization(String message, int errorCode) { 
         super(message); 
         this.message = message; 
         this.errorCode = errorCode; 
     } 
  
     public SharedExceptionAuthorization(String message) { 
         super(message); 
         this.message = message; 
         this.errorCode = 0; // Default error code 
     } 
  
     public SharedExceptionAuthorization() { 
         super(); 
         this.message = null; 
         this.errorCode = 0; 
     } 
  
     @Override 
     public String getMessage() { 
         return message; 
     } 
 } 
 