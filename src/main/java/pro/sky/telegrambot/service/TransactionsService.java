package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Transactions;
import pro.sky.telegrambot.repostory.TransactionsRepository;

import java.util.List;

@Service
public class TransactionsService {

    private final TransactionsRepository transactionsRepository;

    public TransactionsService(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    public List<Transactions> findAllByAccountId(long accountId) {
        return transactionsRepository.findAllByAccountId(accountId);
    }

    public Transactions create(Transactions transactions) {
        return transactionsRepository.save(transactions);
    }

    public void delete(long id) {
        transactionsRepository.deleteById(id);
    }
}
