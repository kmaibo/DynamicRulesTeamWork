package pro.sky.telegrambot.service.bankOperation;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.enums.TypeTransactions;
import pro.sky.telegrambot.model.primary.Account;
import pro.sky.telegrambot.model.primary.Transactions;
import pro.sky.telegrambot.repository.primary.AccountRepository;
import pro.sky.telegrambot.repository.primary.TransactionsRepository;

import java.math.BigDecimal;

@Service
public class DepositService {

    private final Logger log = LoggerFactory.getLogger(DepositService.class);

    private AccountRepository accountRepository;
    private TransactionsRepository transactionsRepository;
    public DepositService(AccountRepository accountRepository, TransactionsRepository transactionsRepository) {
        this.accountRepository = accountRepository;
        this.transactionsRepository = transactionsRepository;
    }

    @Transactional
    public void deposit(long id, BigDecimal amount) {

        log.info("deposit");
        log.error("not implemented");

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        Account dep =  accountRepository.findById(id).orElseThrow();
        Transactions transactions = new Transactions();
        transactions.setType(TypeTransactions.DEPOSIT);
        transactions.setAmount(amount);
        transactions.setAccount(dep);
        transactions.setFromAccountId(id);
        dep.setBalance(dep.getBalance().add(amount));
        accountRepository.save(dep);
        transactionsRepository.save(transactions);
    }
}
