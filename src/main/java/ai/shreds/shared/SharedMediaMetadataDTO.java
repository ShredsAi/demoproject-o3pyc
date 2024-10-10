package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SharedMediaMetadataDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fileName;
    private String fileType;
    private Long fileSize;
    private LocalDateTime uploadTimestamp;
    private String userId;
    private String temporaryStoragePath;
}