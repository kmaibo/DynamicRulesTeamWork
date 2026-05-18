package pro.sky.telegrambot.controller.rule;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@RestController
@RequestMapping("/rule")
@Tag(
        name = "Rule Statistics",
        description = "Statistics for dynamic rules usage")
public class RuleStatsController {

    private final RuleStatRepository statsRepository;
    private final DynamicRuleRepository ruleRepository;

    public RuleStatsController(RuleStatRepository statsRepository,
                               DynamicRuleRepository ruleRepository) {
        this.statsRepository = statsRepository;
        this.ruleRepository = ruleRepository;
    }

    @Operation(summary = "Get rule usage statistics")
    @GetMapping("/stats")
    public ResponseEntity<Map<String, List<RuleStatDto>>> getRuleStats() {
        List<RuleStatDto> statsDtos =
                statsRepository.findAllWithRules()
                        .stream()
                        .map(stat ->
                                new RuleStatDto(
                                        stat.getRule().getId(),
                                        stat.getCount()
                                )
                        )
                        .collect(Collectors.toList());

        Map<String, List<RuleStatDto>> response = new HashMap<>();
        response.put("stats", statsDtos);

        return ResponseEntity.ok(response);
    }
}
