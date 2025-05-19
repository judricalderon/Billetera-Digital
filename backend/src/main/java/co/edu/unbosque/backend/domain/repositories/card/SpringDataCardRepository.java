package co.edu.unbosque.backend.domain.repositories.card;

import co.edu.unbosque.backend.domain.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataCardRepository extends JpaRepository<Card, Long> {
    List<Card> findByUser_IdCliente(Long idCliente);
}
