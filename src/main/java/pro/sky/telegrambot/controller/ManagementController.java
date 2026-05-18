package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.telegrambot.dto.ServiceInfo;

import java.util.Properties;

@RestController
@RequestMapping("/management")
@Tag(name = "Management", description = "System management and monitoring endpoints")
public class ManagementController {

    @Operation(summary = "Clear application caches")
    @PostMapping("/clear-caches")
    public ResponseEntity<Void> clearCaches() {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get service information (name + version)")
    @GetMapping("/info")
    public ResponseEntity<ServiceInfo> getServiceInfo() {
        ServiceInfo info = new ServiceInfo("Recommendation Service", getVersion());
        return ResponseEntity.ok(info);
    }

    private String getVersion() {
        try {
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("build-info.properties"));
            return properties.getProperty("version", "unknown");
        } catch (Exception e) {
            return "unknown";
        }
    }
}
