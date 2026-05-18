package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.primary.Account;
import pro.sky.telegrambot.model.primary.Card;
import pro.sky.telegrambot.repository.primary.AccountRepository;
import pro.sky.telegrambot.repository.primary.CardRepository;

@Service
public class CardService {

    private final Logger log = LoggerFactory.getLogger(CardService.class);

    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;

    public CardService(CardRepository cardRepository,AccountRepository accountRepository) {
        this.cardRepository = cardRepository;
        this.accountRepository = accountRepository;
    }

    public Card create(Card card, long accountId) {

        log.info("card create");
        log.error("not implemented");

        Account account = accountRepository.findById(accountId).orElse(null);
        card.setAccount(account);
        card.setBalance(accountRepository.findById(accountId).get().getBalance());
        return cardRepository.save(card);
    }

    public void delete(long id) {

        log.info("card delete by id " + id);
        log.error("delete card not found");
        cardRepository.deleteById(id);
    }

    public Card findByNumber(String number) {

        log.info("card findByNumber " + number);
        log.error("card not found");
        return cardRepository.findByNumber(number);
    }


}
