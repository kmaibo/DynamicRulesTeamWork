package pro.sky.telegrambot.controller.bankOperation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.service.bankOperation.DepositService;

import java.math.BigDecimal;

/**
 * REST контроллер для пополнения банковского счета.
 */

@RestController
@RequestMapping("/deposit")
public class DepositController {

    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @PatchMapping("/{id}")
    public ResponseEntity deposit(@PathVariable long id, @RequestParam BigDecimal amount) {
        depositService.deposit(id, amount);
        return ResponseEntity.ok().build();
    }
}
