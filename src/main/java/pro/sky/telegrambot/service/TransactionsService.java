package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.primary.Transactions;
import pro.sky.telegrambot.repository.primary.TransactionsRepository;

import java.util.List;

@Service
public class TransactionsService {

    private final Logger log = LoggerFactory.getLogger(TransactionsService.class);

    private final TransactionsRepository transactionsRepository;

    public TransactionsService(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    public List<Transactions> findAllByAccountId(long accountId) {

        log.info("findAllByAccountId " + accountId);
        log.error("findAllByAccountId " + accountId + " not found");
        return transactionsRepository.findAllByAccountId(accountId);
    }

    public Transactions create(Transactions transactions) {

        log.info("transactions " + transactions + " created");
        log.error("transactions " + transactions + " not found");
        return transactionsRepository.save(transactions);
    }

    public void delete(long id) {

        log.info("transaction delete " + id);
        log.error("transaction delete " + id + " not found");

        transactionsRepository.deleteById(id);
    }
}
