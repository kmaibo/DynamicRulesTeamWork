package pro.sky.telegrambot.service.bankOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.enums.TypeTransactions;
import pro.sky.telegrambot.model.primary.Account;
import pro.sky.telegrambot.model.primary.Transactions;
import pro.sky.telegrambot.repository.primary.AccountRepository;
import pro.sky.telegrambot.repository.primary.TransactionsRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class WithdrawService {

    private final Logger log = LoggerFactory.getLogger(WithdrawService.class);

    private final AccountRepository accountRepository;
    private final TransactionsRepository transactionsRepository;

    public WithdrawService(AccountRepository accountRepository, TransactionsRepository transactionsRepository) {
        this.accountRepository = accountRepository;
        this.transactionsRepository = transactionsRepository;
    }

    @Transactional
    public void withdraw(long id, BigDecimal amount) {

        log.info("withdraw");
        log.error("not implemented");

        Account fromAccount = accountRepository.findById(id).orElseThrow();
        Transactions transactions = new Transactions();

        transactions.setType(TypeTransactions.WITHDRAW);
        transactions.setAmount(amount);
        transactions.setAccount(fromAccount);
        transactions.setFromAccountId(id);

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        transactionsRepository.save(transactions);
        accountRepository.save(fromAccount);
    }
}
