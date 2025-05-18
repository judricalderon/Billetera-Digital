package co.edu.unbosque.backend.infrastructure.controllers;

import co.edu.unbosque.backend.application.dtos.CardDTO;
import co.edu.unbosque.backend.application.interfaces.ICardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class CardController {
    private final ICardService cardService;

    public CardController(ICardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/users/{userId}/cards")
    public List<CardDTO> getCardsByUser(@PathVariable Long userId) {
        return cardService.getCardsByUser(userId);
    }

    @PostMapping("/users/{userId}/cards")
    @ResponseStatus(HttpStatus.CREATED)
    public CardDTO addCard(
            @PathVariable Long userId,
            @RequestBody CardDTO cardDTO
    ) {
        cardDTO.setIdCliente(userId);
        return cardService.addCard(cardDTO);
    }

    @PutMapping("/cards/{cardId}/cupo")
    public CardDTO updateCardCupo(
            @PathVariable Long cardId,
            @RequestParam BigDecimal newCupo
    ) {
        return cardService.updateCardCupo(cardId, newCupo);
    }

    @PutMapping("/cards/{cardId}/inactivate")
    public CardDTO inactivateCard(@PathVariable Long cardId) {
        return cardService.inactivateCard(cardId);
    }
}
