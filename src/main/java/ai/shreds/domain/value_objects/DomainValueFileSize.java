package ai.shreds.domain.value_objects; 
  
 import ai.shreds.domain.exceptions.DomainExceptionFileTooLarge; 
  
 public class DomainValueFileSize { 
  
     private static final long MAX_SIZE = 50 * 1024 * 1024; // 50 MB 
  
     private Long value; 
  
     public DomainValueFileSize(Long value) { 
         this.value = value; 
         validate(); 
     } 
  
     public Long getValue() { 
         return value; 
     } 
  
     public void validate() { 
         if (value == null || value <= 0) { 
             throw new DomainExceptionFileTooLarge("File size must be a positive number."); 
         } 
         if (value > MAX_SIZE) { 
             throw new DomainExceptionFileTooLarge("File size exceeds maximum allowed size of " + MAX_SIZE + " bytes."); 
         } 
     } 
 }