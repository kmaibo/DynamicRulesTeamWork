package pro.sky.telegrambot.repository.telegram;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.secondary.RuleEntity;
import pro.sky.telegrambot.model.telegram.RuleStat;

import java.util.Optional;
import java.util.UUID;

public interface RuleStatRepository extends JpaRepository<RuleStat, Long> {
    Optional<RuleStat> findByRuleId(UUID ruleId);

    void deleteByRule(RuleEntity rule);

}
