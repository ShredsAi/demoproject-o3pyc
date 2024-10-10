package ai.shreds.application.exceptions; 
  
 import ai.shreds.shared.SharedExceptionValidation; 
  
 public class ApplicationExceptionValidation extends SharedExceptionValidation { 
  
     public ApplicationExceptionValidation(String message, int errorCode) { 
         super(message, errorCode); 
     } 
 } 
 