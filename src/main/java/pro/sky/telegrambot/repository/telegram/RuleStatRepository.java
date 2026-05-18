package pro.sky.telegrambot.repository.telegram;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.telegrambot.model.secondary.RuleEntity;
import pro.sky.telegrambot.model.telegram.RuleStat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RuleStatRepository extends JpaRepository<RuleStat, Long> {
    Optional<RuleStat> findByRuleId(UUID ruleId);

    @Query("""
        SELECT rs
        FROM RuleStat rs
        JOIN FETCH rs.rule
    """)
    List<RuleStat> findAllWithRules();

}
