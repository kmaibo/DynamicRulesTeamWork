package pro.sky.telegrambot.controller.rule;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.telegrambot.dto.RuleStatDto;
import pro.sky.telegrambot.model.secondary.RuleEntity;
import pro.sky.telegrambot.model.telegram.RuleStat;
import pro.sky.telegrambot.repository.secondary.DynamicRuleRepository;
import pro.sky.telegrambot.repository.telegram.RuleStatRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST контроллер статистики динамических правил рекомендаций.
 * Возвращает количество срабатываний каждого правила.
 */

@RestController
@RequestMapping("/rule")
public class RuleStatsController {

    private final RuleStatRepository statsRepository;
    private final DynamicRuleRepository ruleRepository;

    public RuleStatsController(RuleStatRepository statsRepository,
                               DynamicRuleRepository ruleRepository) {
        this.statsRepository = statsRepository;
        this.ruleRepository = ruleRepository;
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, List<RuleStatDto>>> getRuleStats() {
        List<RuleEntity> allRules = ruleRepository.findAll();
        List<RuleStatDto> statsDtos = allRules.stream()
                .map(rule -> {
                    Optional<RuleStat> statsOpt = statsRepository.findByRuleId(rule.getId());
                    Long count = statsOpt.map(RuleStat::getCount).orElse(0L);
                    return new RuleStatDto(rule.getId(), count);
                })
                .collect(Collectors.toList());
        Map<String, List<RuleStatDto>> response = new HashMap<>();
        response.put("stats", statsDtos);
        return ResponseEntity.ok(response);
    }
}
