package pro.sky.telegrambot.controller.bankOperation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.primary.Card;
import pro.sky.telegrambot.service.CardService;

@RestController
@RequestMapping("/cards")
@Tag(name = "Cards", description = "Cards management API")

public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @Operation(summary = "Create")
    @PostMapping
    public ResponseEntity<Card> create(@Parameter(description = "Card") @RequestParam Card card,@Parameter(description = "Account id") @RequestParam long accountId) {
        cardService.create(card, accountId);
        return ResponseEntity.status(HttpStatus.CREATED).body(card);
    }

    @Operation(summary = "Delete")
    @DeleteMapping("/{id}")
    public ResponseEntity<Card> delete(@Parameter(description = "Card id") @PathVariable long id) {
        cardService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get card by number")
    @GetMapping("/{number}")
    public ResponseEntity<Card> findByNumber(@Parameter(description = "Card number") @PathVariable String number) {
        Card card = cardService.findByNumber(number);

        if (card == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(card);
    }
}
