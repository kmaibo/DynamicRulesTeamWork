package pro.sky.telegrambot.service.bankOperation;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.enums.TypeTransactions;
import pro.sky.telegrambot.model.primary.Account;
import pro.sky.telegrambot.model.primary.Transactions;
import pro.sky.telegrambot.repository.primary.AccountRepository;
import pro.sky.telegrambot.repository.primary.TransactionsRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

/**
 * Сервис для выполнения операций снятие средств со счета.
 * Обеспечивает контролируемое снятие средств с проверкой входных данных
 */

@Service
public class WithdrawService {

    private final AccountRepository accountRepository;
    private final TransactionsRepository transactionsRepository;

    public WithdrawService(AccountRepository accountRepository, TransactionsRepository transactionsRepository) {
        this.accountRepository = accountRepository;
        this.transactionsRepository = transactionsRepository;
    }

    /**
     * Метод выполняет операцию снятие средств
     * При выполнении создается транзакция о снятии средств со счета
     * @param amount сумма которую нужно снять
     * @param id служит для поиска аккаунта с которого хотят снять средства
     */

    @Transactional
    public void withdraw(long id, BigDecimal amount) {
        Account fromAccount = accountRepository.findById(id).orElseThrow();
        Transactions transactions = new Transactions();

        if (amount.compareTo(fromAccount.getBalance()) < 0) {
            throw new IllegalArgumentException("There are not enough funds in the account");
        }
        transactions.setType(TypeTransactions.WITHDRAW);
        transactions.setAmount(amount);
        transactions.setAccount(fromAccount);
        transactions.setFromAccountId(id);

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        transactionsRepository.save(transactions);
        accountRepository.save(fromAccount);
    }
}
