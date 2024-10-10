package ai.shreds.domain.ports; 
  
 import ai.shreds.domain.entities.DomainEntityMediaFile; 
  
 public interface DomainPortMediaRepository { 
  
     /** 
      * Saves the provided DomainEntityMediaFile to the data source. 
      * 
      * @param mediaFile the media file entity to save 
      */ 
     void save(DomainEntityMediaFile mediaFile); 
  
     /** 
      * Finds a DomainEntityMediaFile by its temporaryMediaId. 
      * 
      * @param temporaryMediaId the temporary media ID to search for 
      * @return the found DomainEntityMediaFile, or null if not found 
      */ 
     DomainEntityMediaFile findByTemporaryMediaId(String temporaryMediaId); 
 } 
 