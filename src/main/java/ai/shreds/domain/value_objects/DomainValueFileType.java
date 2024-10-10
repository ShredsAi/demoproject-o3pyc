package ai.shreds.domain.value_objects; 
  
 import ai.shreds.domain.exceptions.DomainExceptionInvalidMediaType; 
  
 import java.util.Arrays; 
 import java.util.List; 
  
 public class DomainValueFileType { 
  
     private String value; 
     private static final List<String> ALLOWED_MEDIA_TYPES = Arrays.asList( 
             "image/jpeg", 
             "image/png", 
             "video/mp4" 
     ); 
  
     public DomainValueFileType(String value) { 
         this.value = value; 
         validate(); 
     } 
  
     public String getValue() { 
         return value; 
     } 
  
     public void validate() { 
         if (!ALLOWED_MEDIA_TYPES.contains(this.value)) { 
             throw new DomainExceptionInvalidMediaType("Invalid media file type. Supported types are JPEG, PNG, and MP4."); 
         } 
     } 
 }