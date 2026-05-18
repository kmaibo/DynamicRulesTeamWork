package pro.sky.telegrambot.controller.bankOperation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.service.bankOperation.WithdrawService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/withdraw")
@Tag(name = "Withdraw", description = "Withdraw money operation")
public class WithdrawController {

    private final WithdrawService withdrawService;

    public WithdrawController(WithdrawService withdrawService) {
        this.withdrawService = withdrawService;
    }

    @Operation(summary = "Withdraw money this account")
    @PatchMapping("/{id}")
    public ResponseEntity withdraw(@Parameter(description = "Account id") @PathVariable long id,
                                   @Parameter(description = "Withdraw amount") @RequestParam BigDecimal amount) {

        withdrawService.withdraw(id, amount);
        return ResponseEntity.ok().build();
    }
}
