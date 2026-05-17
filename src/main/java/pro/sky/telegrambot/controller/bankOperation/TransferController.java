package pro.sky.telegrambot.controller.bankOperation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.telegrambot.service.bankOperation.TransferService;

import java.math.BigDecimal;

/**
 * REST контроллер для перевода средств между банковскими счетами
 */

@RestController
@RequestMapping("/transfer")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PatchMapping("/transferByPhone")
    public ResponseEntity transfer(String fromId, String toId, BigDecimal amount) {
        transferService.transfer(fromId, toId, amount);
        return ResponseEntity.ok().build();
    }

}
