package pro.sky.telegrambot.service.rule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.dto.DynamicRuleDto;

import java.util.UUID;

/**
 * Сервис генерации персонализированных рекомендаций для пользователей.
 */
@Service
@RequiredArgsConstructor
public class DynamicRuleEvaluator {

    private final UserKnowledgeRepository userKnowledgeRepository;

    /**
     * Проверяет выполнение всех условий правил для пользователя.
     *
     * @param rule правило с набором условий
     */

    public boolean evaluate(DynamicRuleDto rule, UUID userId) {
        if (rule.getRule() == null) return true;

        for (DynamicRuleDto.QueryConditionDto cond : rule.getRule()) {
            boolean result = evaluateCondition(userId, cond);
            // Инвертируем результат условия, если стоит negate
            if (cond.isNegate()) {
                result = !result;
            }
            // Если хотя бы одно условие не выполнено — правило не проходит
            if (!result) {
                return false;
            }
        }
        return true;
    }

    private boolean evaluateCondition(UUID userId, DynamicRuleDto.QueryConditionDto cond) {
        return switch (cond.getQuery()) {
            case "USER_OF" -> userKnowledgeRepository.isUserOf(userId, cond.getArguments().get(0));
            case "ACTIVE_USER_OF" -> userKnowledgeRepository.isActiveUserOf(userId, cond.getArguments().get(0));
            case "TRANSACTION_SUM_COMPARE" -> {
                var args = cond.getArguments();
                yield userKnowledgeRepository.compareTransactionSum(
                        userId,
                        args.get(0),
                        args.get(1),
                        args.get(2),
                        Integer.parseInt(args.get(3))
                );
            }
            case "TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW" -> {
                var args = cond.getArguments();
                yield userKnowledgeRepository.compareDepositWithdraw(
                        userId,
                        args.get(0),
                        args.get(1)
                );
            }
            default -> throw new IllegalArgumentException("неизвестный запрос: " + cond.getQuery());
        };
    }
}