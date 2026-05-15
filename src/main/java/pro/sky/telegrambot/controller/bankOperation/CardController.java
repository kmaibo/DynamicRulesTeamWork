package pro.sky.telegrambot.controller.bankOperation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.primary.Card;
import pro.sky.telegrambot.service.CardService;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<Card> create(Card card, @RequestParam long accountId) {
        cardService.create(card,accountId);
        return ResponseEntity.status(HttpStatus.CREATED).body(card);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Card> delete(@PathVariable long id) {
        cardService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{number}")
    public ResponseEntity<Card> findByNumber(@PathVariable String number) {
        cardService.findByNumber(number);
        if (cardService.findByNumber(number) != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(cardService.findByNumber(number));
    }
}
