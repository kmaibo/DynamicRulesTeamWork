package pro.sky.telegrambot.service.bankOperation;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.enums.TypeTransactions;
import pro.sky.telegrambot.model.primary.Account;
import pro.sky.telegrambot.model.primary.Transactions;
import pro.sky.telegrambot.repository.primary.AccountRepository;
import pro.sky.telegrambot.repository.primary.TransactionsRepository;

import java.math.BigDecimal;

/**
 * Сервис для выполнения операций переводов между счетами.
 * Обеспечивает контролируемый перевод средств с проверкой входных данных
 */

@Service
public class TransferService {

    private final TransactionsRepository transactionsRepository;
    private final AccountRepository accountRepository;

    public TransferService(TransactionsRepository transactionsRepository,
                           AccountRepository accountRepository) {
        this.transactionsRepository = transactionsRepository;
        this.accountRepository = accountRepository;
    }
/**
 * Метод выполняет операцию перевода средств между счетами банковского аккаунта.
 * При выполнении метода создается транзакция о переводе у обоих аккаунтов
 * @param amount сумма перевода
 * @param fromId служит для поиска аккаунта с которого осуществляется перевод
 * @param toId служит для поиска аккаунта которому этот перевод предназначен
 */
    @Transactional
    public void transfer(String fromId, String toId, BigDecimal amount) {
        Account fromAccount = accountRepository.findByPhone(fromId);
        Account toAccount = accountRepository.findByPhone(toId);
        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Amount must be greater than or equal to Balance");
        }
        Transactions transactions = new Transactions();
        transactions.setType(TypeTransactions.TRANSFER);
        transactions.setAccount(fromAccount);
        transactions.setFromAccountId(fromAccount.getId());
        transactions.setToAccountId(toAccount.getId());
        transactions.setAmount(amount);
        transactionsRepository.save(transactions);

        Transactions deposit = new Transactions();
        deposit.setType(TypeTransactions.TRANSFER);
        deposit.setAccount(toAccount);
        deposit.setAmount(amount);
        deposit.setFromAccountId(fromAccount.getId());
        deposit.setToAccountId(toAccount.getId());
        transactionsRepository.save(deposit);

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }
}
