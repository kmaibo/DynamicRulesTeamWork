package pro.sky.telegrambot.repostory.rule;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.rule.RuleEntity;


import java.util.Optional;
import java.util.UUID;

public interface DynamicRuleRepository extends JpaRepository<RuleEntity, UUID> {
    void deleteByProductId(UUID productId);
    Optional<RuleEntity> findByProductId(UUID productId);
    boolean existsByProductId(UUID productId);
}