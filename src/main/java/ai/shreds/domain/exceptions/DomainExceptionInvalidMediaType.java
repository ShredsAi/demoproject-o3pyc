package ai.shreds.domain.exceptions; 
  
 public class DomainExceptionInvalidMediaType extends RuntimeException { 
  
     private static final String DEFAULT_MESSAGE = "Invalid media type. Supported types are JPEG, PNG, and MP4."; 
  
     public DomainExceptionInvalidMediaType() { 
         super(DEFAULT_MESSAGE); 
     } 
  
     public DomainExceptionInvalidMediaType(String invalidMediaType) { 
         super("Invalid media type: " + invalidMediaType + ". Supported types are JPEG, PNG, and MP4."); 
     } 
  
 } 
 