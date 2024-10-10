package ai.shreds.infrastructure.external_services;

import ai.shreds.application.ports.ApplicationOutputPortTemporaryStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class InfrastructureClientTemporaryStorage implements ApplicationOutputPortTemporaryStorage {

    private static final Logger log = LoggerFactory.getLogger(InfrastructureClientTemporaryStorage.class);

    @Value("${temp.storage.directory}")
    private String tempStorageDir;

    @Override
    public String storeFileTemporarily(MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.lastIndexOf('.') != -1) {
                extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
            }

            String uniqueFilename = UUID.randomUUID().toString() + extension;

            Path storageDirectory = Paths.get(tempStorageDir);
            if (!Files.exists(storageDirectory)) {
                Files.createDirectories(storageDirectory);
            }

            Path destinationFile = storageDirectory.resolve(uniqueFilename);

            try (var inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }

            return destinationFile.toAbsolutePath().toString();

        } catch (IOException e) {
            log.error("Failed to store file temporarily", e);
            throw new RuntimeException("Failed to store file temporarily", e);
        }
    }
}