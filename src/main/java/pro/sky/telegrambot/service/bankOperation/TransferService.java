package pro.sky.telegrambot.service.bankOperation;

import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.enums.TypeTransactions;
import pro.sky.telegrambot.model.primary.Account;
import pro.sky.telegrambot.model.primary.Transactions;
import pro.sky.telegrambot.repository.primary.AccountRepository;
import pro.sky.telegrambot.repository.primary.CardRepository;
import pro.sky.telegrambot.repository.primary.TransactionsRepository;

import java.math.BigDecimal;

@Service
public class TransferService {

    private final Logger log = LoggerFactory.getLogger(TransferService.class);

    private final TransactionsRepository transactionsRepository;
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;

    public TransferService(TransactionsRepository transactionsRepository,
                           AccountRepository accountRepository,
                           CardRepository cardRepository) {
        this.transactionsRepository = transactionsRepository;
        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository;
    }

    @Transactional
    public void transfer(String fromId, String toId, BigDecimal amount) {

        log.info("transfer");
        log.error("not implemented");

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
