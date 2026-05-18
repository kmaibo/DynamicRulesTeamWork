package pro.sky.telegrambot.controller.bankOperation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.service.bankOperation.WithdrawService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/withdraw")
public class WithdrawController {

    private final WithdrawService withdrawService;

    public WithdrawController(WithdrawService withdrawService) {
        this.withdrawService = withdrawService;
    }

    @PatchMapping("/{id}")
    public ResponseEntity withdraw(@PathVariable long id, @RequestParam BigDecimal amount) {
        withdrawService.withdraw(id, amount);
        return ResponseEntity.ok().build();
    }
}
