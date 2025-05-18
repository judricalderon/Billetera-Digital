package co.edu.unbosque.backend.domain.interfaces;

import co.edu.unbosque.backend.domain.model.Card;
import java.util.List;
import java.util.Optional;

public interface ICardRepository {
    List<Card> findByUserId(Long idCliente);
    Optional<Card> findById(Long idTarjeta);
    Card save(Card cardEntity);
}
