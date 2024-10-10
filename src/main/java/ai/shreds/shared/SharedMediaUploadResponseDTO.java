package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedMediaUploadResponseDTO {
    private String status;
    private String message;
    private String temporaryMediaId;
    private String uploadStatus;
}