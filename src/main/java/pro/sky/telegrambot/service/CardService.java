package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Account;
import pro.sky.telegrambot.model.Card;
import pro.sky.telegrambot.repostory.AccountRepository;
import pro.sky.telegrambot.repostory.CardRepository;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;

    public CardService(CardRepository cardRepository,AccountRepository accountRepository) {
        this.cardRepository = cardRepository;
        this.accountRepository = accountRepository;
    }

    public Card create(Card card, long accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        card.setAccount(account);
        card.setBalance(accountRepository.findById(accountId).get().getBalance());
        return cardRepository.save(card);
    }

    public void delete(long id) {
        cardRepository.deleteById(id);
    }

    public Card findByNumber(String number) {
        return cardRepository.findByNumber(number);
    }


}
