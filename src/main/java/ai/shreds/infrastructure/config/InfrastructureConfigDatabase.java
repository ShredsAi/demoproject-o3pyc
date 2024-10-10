package ai.shreds.infrastructure.config; 
  
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.context.annotation.Bean; 
 import org.springframework.beans.factory.annotation.Value; 
 import org.springframework.data.mongodb.core.MongoTemplate; 
 import org.springframework.data.mongodb.core.index.IndexOperations; 
 import org.springframework.data.mongodb.core.index.Index; 
 import org.springframework.data.domain.Sort.Direction; 
 import com.mongodb.client.MongoClient; 
 import com.mongodb.client.MongoClients; 
  
 @Configuration 
 public class InfrastructureConfigDatabase { 
  
     @Value("${spring.data.mongodb.uri}") 
     private String mongoUri; 
  
     @Value("${spring.data.mongodb.database}") 
     private String mongoDatabase; 
  
     @Bean 
     public MongoClient mongoClient() { 
         return MongoClients.create(mongoUri); 
     } 
  
     @Bean 
     public MongoTemplate mongoTemplate() { 
         return new MongoTemplate(mongoClient(), mongoDatabase); 
     } 
  
     public void configure() { 
         // Set up an index on temporaryMediaId 
         MongoTemplate template = mongoTemplate(); 
         IndexOperations indexOps = template.indexOps("MediaFiles"); 
         Index index = new Index("temporaryMediaId", Direction.ASC).unique(); 
         indexOps.ensureIndex(index); 
     } 
 } 
 