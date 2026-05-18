package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.primary.Account;
import pro.sky.telegrambot.repository.primary.AccountRepository;

@Service
public class AccountService {

    private final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private final AccountRepository accountRepository;
    private final UserService userService;

    public AccountService(AccountRepository accountRepository, UserService userService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
    }

    public Account findById(Long id) {

        logger.info("find by id");
        logger.error("find by id not found");
        return accountRepository.findById(id).orElseThrow();
    }

    public Account create(Account account) {

        logger.info("create account");
        logger.error("create account not found");

        return accountRepository.save(account);
    }

    public Account update(long id, Account account) {

        logger.info("find by id " + id);
        logger.error("find by id not found");
        Account edited = accountRepository.findById(id).orElseThrow();
        edited.setPhone(account.getPhone());
        edited.setEmail(account.getEmail());
        edited.setPassword(account.getPassword());
        logger.info("update account");
        logger.error("update account not found");
        return accountRepository.save(edited);
    }

    public void delete(long id) {
        logger.info("delete account");
        logger.error("delete account not found");
        accountRepository.deleteById(id);
    }
}
