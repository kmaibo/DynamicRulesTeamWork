
package pro.sky.telegrambot.controller.rule;

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

/**
 * REST контроллер для получения персонализированных рекомендаций.
 * Фильтрует динамические правила по пользователю и возвращает применимые рекомендации.
 */

@RestController
@RequestMapping("/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    private final DynamicRuleService dynamicRuleService;
    private final DynamicRuleEvaluator dynamicRuleEvaluator;

    @GetMapping("/{userId}")
    public ResponseEntity<List<DynamicRuleDto>> getRecommendations(@PathVariable UUID userId) {
        List<DynamicRuleDto> allRules = dynamicRuleService.getAllRules();
        List<DynamicRuleDto> applicable = allRules.stream()
                .filter(rule -> dynamicRuleEvaluator.evaluate(rule, userId))
                .collect(Collectors.toList());
        return ResponseEntity.ok(applicable);
    }
}