package pro.sky.telegrambot.service.rule;

import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Репозиторий пользовательских знаний и аналитических проверок.
 * Предоставляет методы для вычисления пользовательских
 * характеристик и проверки бизнес-условий на основе
 * транзакционной активности.
 */

@Repository
public class UserKnowledgeRepository {

    /**
     * Проверяет, является ли пользователь клиентом продукта.
     * SQL-логика: ищет связь user-product в таблице user_products
     */

    public boolean isUserOf(UUID userId, String productType) {

        return false;
    }

    /**
     * Проверяет активность пользователя по продукту.
     * Активный пользователь — тот, кто совершал операции за последние N дней.
     */

    public boolean isActiveUserOf(UUID userId, String productType) {

        return false;
    }

    /**
     * Сравнивает сумму транзакций пользователя с заданным условием.
     * Поддерживает операторы: >, <, =, >=, <=
     */

    public boolean compareTransactionSum(UUID userId, String productType,
                                         String transactionType, String operator, int constant) {

        return false;
    }

    public boolean compareDepositWithdraw(UUID userId, String productType, String operator) {

        return false;
    }
}