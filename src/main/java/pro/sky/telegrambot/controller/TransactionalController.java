package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.primary.Transactions;
import pro.sky.telegrambot.service.TransactionsService;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transactions", description = "Transactions management API")
public class TransactionalController {

    private final TransactionsService transactionsService;

    public TransactionalController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @Operation(summary = "Find all account transactions by ID")
    @GetMapping("/{accountId}")
    public List<Transactions> findAllById(@Parameter(description = "Account ID") @PathVariable long accountId) {
        return transactionsService.findAllByAccountId(accountId);
    }

    @Operation(summary = "Delete transaction by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@Parameter(description = "Transaction ID") @PathVariable long id) {
        transactionsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
