package co.edu.unbosque.backend.domain.repositories.card;

import co.edu.unbosque.backend.domain.interfaces.ICardRepository;
import co.edu.unbosque.backend.domain.model.Card;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CardRepositoryAdapter implements ICardRepository {
    private final SpringDataCardRepository jpa;

    public CardRepositoryAdapter(SpringDataCardRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public List<Card> findByUserId(Long idCliente) {
        return jpa.findByUser_IdCliente(idCliente);
    }

    @Override
    public Optional<Card> findById(Long idTarjeta) {
        return jpa.findById(idTarjeta);
    }

    @Override
    public Card save(Card cardEntity) {
        return jpa.save(cardEntity);
    }
}
