package pro.sky.telegrambot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.primary.Transactions;
import pro.sky.telegrambot.service.TransactionsService;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionalController {

    private final TransactionsService transactionsService;

    public TransactionalController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @GetMapping("/{accountId}")
    public List<Transactions> findAllById(@PathVariable long accountId) {
        return transactionsService.findAllByAccountId(accountId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        transactionsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
