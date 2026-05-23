package pro.sky.telegrambot.controller.bankOperation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.telegrambot.service.bankOperation.DepositService;


import java.math.BigDecimal;

@RestController
@RequestMapping("/deposit")
public class DepositController {

    private final DepositService depositService;
    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @PatchMapping("/{id}")
    public ResponseEntity deposit(@PathVariable long id, BigDecimal amount) {
        depositService.deposit(id, amount);
        return ResponseEntity.ok().build();
    }
}
