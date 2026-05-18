package pro.sky.telegrambot.controller.bankOperation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.service.bankOperation.DepositService;


import java.math.BigDecimal;

@RestController
@RequestMapping("/deposit")
@Tag(name = "Deposit",
        description = "Operations for depositing money into account ")
public class DepositController {

    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @Operation(summary = "Deposit money to account")
    @PatchMapping("/{id}")
    public ResponseEntity deposit(
            @Parameter(description = "Account id")
            @PathVariable long id,
            @Parameter(description = "Deposit amount")
            @RequestParam BigDecimal amount) {
        depositService.deposit(id, amount);
        return ResponseEntity.ok().build();
    }
}
