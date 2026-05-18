package pro.sky.telegrambot.controller.bankOperation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.telegrambot.service.bankOperation.TransferService;


import java.math.BigDecimal;

@RestController
@RequestMapping("/transfer")
@Tag(name = "transfer",
        description = "Money transfer operation")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @Operation(summary = "Transfer money between accounts")
    @PatchMapping("/transferByPhone")
    public ResponseEntity transfer(@Parameter(description = "Sender phone number")
                                   @RequestParam String fromId,

                                   @Parameter(description = "Receiver phone number")
                                   @RequestParam String toId,

                                   @Parameter(description = "Transfer amount")
                                   @RequestParam BigDecimal amount) {
        transferService.transfer(fromId, toId, amount);

        return ResponseEntity.ok().build();
    }

}
