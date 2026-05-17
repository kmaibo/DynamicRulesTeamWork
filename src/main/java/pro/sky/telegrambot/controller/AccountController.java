package pro.sky.telegrambot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.primary.Account;
import pro.sky.telegrambot.service.AccountService;

import java.net.URI;

/**
 * REST контроллер для управления банковскими счетами.
 */

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.notFound().build();
        }
        accountService.findById(id);
        return ResponseEntity.ok().body(accountService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        if (account == null) {
            return ResponseEntity.notFound().build();
        }

        accountService.create(account);
        return ResponseEntity.created(URI.create("/accounts/" + account.getId())).body(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        if (id == null) {
            return ResponseEntity.notFound().build();
        }

        accountService.update(id, account);
        return ResponseEntity.ok().body(accountService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Account> deleteAccount(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.notFound().build();
        }

        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
