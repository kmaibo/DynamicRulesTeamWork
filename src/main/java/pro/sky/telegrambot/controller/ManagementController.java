package pro.sky.telegrambot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.telegrambot.dto.ServiceInfo;

import java.util.Properties;

/**
 * REST контроллер для служебных операций управления сервисом.
 * Предоставляет эндпоинты для получения информации о сервисе
 * и выполнения административных действий.
 */

@RestController
@RequestMapping("/management")
public class ManagementController {

    @PostMapping("/clear-caches")
    public ResponseEntity<Void> clearCaches() {
        return ResponseEntity.ok().build();
    }

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
