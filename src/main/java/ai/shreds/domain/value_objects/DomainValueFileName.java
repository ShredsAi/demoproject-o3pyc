package ai.shreds.domain.value_objects; 
  
 import lombok.Getter; 
  
 @Getter 
 public class DomainValueFileName { 
  
     private final String value; 
  
     public DomainValueFileName(String value) { 
         this.value = value; 
         validate(); 
     } 
  
     public void validate() { 
         if (value == null || value.trim().isEmpty()) { 
             throw new IllegalArgumentException("File name cannot be null or empty."); 
         } 
         if (value.length() > 255) { 
             throw new IllegalArgumentException("File name cannot exceed 255 characters."); 
         } 
         if (value.contains("..") || value.contains("/") || value.contains("\\")) { 
             throw new IllegalArgumentException("File name contains invalid characters."); 
         } 
         // Additional validation rules can be added here 
     } 
 } 
 