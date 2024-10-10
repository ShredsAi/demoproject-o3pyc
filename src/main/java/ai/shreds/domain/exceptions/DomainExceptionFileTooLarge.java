package ai.shreds.domain.exceptions; 
  
 public class DomainExceptionFileTooLarge extends RuntimeException { 
  
     public DomainExceptionFileTooLarge() { 
         super("Media file size exceeds the maximum allowed limit."); 
     } 
  
     public DomainExceptionFileTooLarge(String message) { 
         super(message); 
     } 
  
     @Override 
     public String getMessage() { 
         return super.getMessage(); 
     } 
 } 
 