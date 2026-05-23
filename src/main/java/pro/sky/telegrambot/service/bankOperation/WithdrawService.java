package pro.sky.telegrambot.service.bankOperation;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.enums.TypeTransactions;
import pro.sky.telegrambot.model.Account;
import pro.sky.telegrambot.model.Transactions;
import pro.sky.telegrambot.repostory.AccountRepository;
import pro.sky.telegrambot.repostory.TransactionsRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class WithdrawService {

    private final AccountRepository accountRepository;
    private final TransactionsRepository transactionsRepository;

    public WithdrawService(AccountRepository accountRepository, TransactionsRepository transactionsRepository) {
        this.accountRepository = accountRepository;
        this.transactionsRepository = transactionsRepository;
    }

    @Transactional
    public void withdraw(long id, BigDecimal amount) {
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
