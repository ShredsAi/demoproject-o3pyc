package ai.shreds.infrastructure.external_services.clients;

import ai.shreds.infrastructure.external_services.dto.CheckPermissionRequest;
import ai.shreds.infrastructure.external_services.dto.CheckPermissionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authorization-service", url = "${authorization.service.url}")
public interface AuthorizationServiceFeignClient {

    @PostMapping("/check-permission")
    CheckPermissionResponse checkPermission(@RequestBody CheckPermissionRequest request);
}