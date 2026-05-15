package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Account;
import pro.sky.telegrambot.repostory.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;

    public AccountService(AccountRepository accountRepository, UserService userService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
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
        Account edited =  accountRepository.findById(id).orElseThrow();
        edited.setPhone(account.getPhone());
        edited.setEmail(account.getEmail());
        edited.setPassword(account.getPassword());
        return accountRepository.save(edited);
    }

    public void delete(long id) {
        accountRepository.deleteById(id);
    }
}
