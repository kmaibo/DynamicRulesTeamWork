
package pro.sky.telegrambot.controller.rule;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.telegrambot.dto.DynamicRuleDto;
import pro.sky.telegrambot.service.rule.DynamicRuleEvaluator;
import pro.sky.telegrambot.service.rule.DynamicRuleService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recommendation")
@RequiredArgsConstructor
@Tag(name = "Recommendations", description = "Rule-based recommendation engine API")
public class RecommendationController {

    private final DynamicRuleService dynamicRuleService;
    private final DynamicRuleEvaluator dynamicRuleEvaluator;

    @Operation(summary = "Get personalized recommendations for user")
    @GetMapping("/{userId}")
    public ResponseEntity<List<DynamicRuleDto>> getRecommendations(@Parameter(description = "Telegram Bot User UUID") @PathVariable UUID userId) {
        List<DynamicRuleDto> allRules = dynamicRuleService.getAllRules();
        List<DynamicRuleDto> applicable = allRules.stream()
                .filter(rule -> dynamicRuleEvaluator.evaluate(rule, userId))
                .collect(Collectors.toList());
        return ResponseEntity.ok(applicable);
    }
}