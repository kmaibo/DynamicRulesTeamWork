package pro.sky.telegrambot.controller.rule;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.dto.DynamicRuleDto;
import pro.sky.telegrambot.dto.RulesListResponse;
import pro.sky.telegrambot.service.rule.DynamicRuleService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rule")
@RequiredArgsConstructor
@Tag(
        name = "Rules",
        description = "Dynamic rule management API")
public class RuleController {

    private final DynamicRuleService ruleService;

    @Operation(summary = "Create new rule")
    @PostMapping
    public ResponseEntity<DynamicRuleDto> createRule(@RequestBody @Valid DynamicRuleDto request) {
        DynamicRuleDto created = ruleService.createRule(request);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Get all rules")
    @GetMapping
    public ResponseEntity<RulesListResponse> getAllRules() {
        List<DynamicRuleDto> rules = ruleService.getAllRules();
        return ResponseEntity.ok(new RulesListResponse(rules));
    }

    @Operation(summary = "Delete rule by productId")
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteRule(
            @Parameter(description = "Product UUID")
            @PathVariable UUID productId) {
        ruleService.deleteRule(productId);
        return ResponseEntity.noContent().build();
    }
}