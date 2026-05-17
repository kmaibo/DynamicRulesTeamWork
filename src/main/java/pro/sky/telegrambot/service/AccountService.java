package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.primary.Account;
import pro.sky.telegrambot.repository.primary.AccountRepository;

/**
 * Сервис для управления аккаунтами пользователей.
 * Представляет CRUD-операции для сущностей Account
 */

@Service
public class AccountService {

    private final AccountRepository accountRepository;


    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow();
    }

    public Account findByPhone(String phone) {
        return accountRepository.findByPhone(phone);
    }

    public Account create(Account account) {
        return accountRepository.save(account);
    }

    public Account update(long id, Account account) {
        Account edited = accountRepository.findById(id).orElseThrow();
        edited.setPhone(account.getPhone());
        edited.setEmail(account.getEmail());
        edited.setPassword(account.getPassword());
        return accountRepository.save(edited);
    }

    public void delete(long id) {
        accountRepository.deleteById(id);
    }
}
