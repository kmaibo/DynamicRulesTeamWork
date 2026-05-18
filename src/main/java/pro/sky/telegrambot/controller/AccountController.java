package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.primary.Account;
import pro.sky.telegrambot.service.AccountService;

import java.net.URI;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Accounts", description = "Account management API")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "Find account by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@Parameter(description = "Account ID") @PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.notFound().build();
        }
        Account account = accountService.findById(id);
        return ResponseEntity.ok(account);
    }

    @Operation(summary = "Create account")
    @PostMapping
    public ResponseEntity<Account> createAccount(@Parameter(description = "Account")@RequestBody Account account) {
        if (account == null) {
            return ResponseEntity.notFound().build();
        }

        accountService.create(account);
        return ResponseEntity.created(URI.create("/accounts/" + account.getId())).body(account);
    }

    @Operation(summary = "Update account by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@Parameter(description = "Account ID") @PathVariable Long id,@Parameter(description = "Account") @RequestBody Account account) {
        if (id == null) {
            return ResponseEntity.notFound().build();
        }

        accountService.update(id, account);
        return ResponseEntity.ok().body(accountService.findById(id));
    }

    @Operation(summary = "Delete account by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Account> deleteAccount(@Parameter(description = "Account ID") @PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.notFound().build();
        }

        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
