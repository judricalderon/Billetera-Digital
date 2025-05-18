package co.edu.unbosque.backend.application.services;

import co.edu.unbosque.backend.application.dtos.CardDTO;
import co.edu.unbosque.backend.application.interfaces.ICardService;
import co.edu.unbosque.backend.domain.interfaces.ICardRepository;
import co.edu.unbosque.backend.domain.interfaces.IUserRepository;
import co.edu.unbosque.backend.domain.model.Card;
import co.edu.unbosque.backend.domain.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService implements ICardService {
    private final ICardRepository repository;
    private final IUserRepository userRepository;

    public CardService(
            ICardRepository repository,
            IUserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    private CardDTO toDTO(Card e) {
        return new CardDTO(
                e.getIdTarjeta(),
                e.getNumero(),
                e.getFechaVencimiento(),
                e.getFranquicia(),
                e.getEstado(),
                e.getCupoTotal(),
                e.getCupoDisponible(),
                e.getCupoUtilizado(),
                e.getUser().getIdCliente()
        );
    }

    private Card toEntity(CardDTO d) {
        Card e = new Card();
        e.setIdTarjeta(d.getIdTarjeta());
        e.setNumero(d.getNumero());
        e.setFechaVencimiento(d.getFechaVencimiento());
        e.setFranquicia(d.getFranquicia());
        e.setEstado(d.getEstado());
        e.setCupoTotal(d.getCupoTotal());
        e.setCupoDisponible(d.getCupoDisponible());
        e.setCupoUtilizado(d.getCupoUtilizado());
        return e;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CardDTO> getCardsByUser(Long idCliente) {
        return repository.findByUserId(idCliente).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CardDTO addCard(CardDTO cardDTO) {
        User user = userRepository.findById(cardDTO.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Card e = toEntity(cardDTO);
        e.setEstado("Activo");
        e.setUser(user);
        Card saved = repository.save(e);
        return toDTO(saved);
    }

    @Override
    @Transactional
    public CardDTO updateCardCupo(Long idTarjeta, BigDecimal newCupoDisponible) {
        Card e = repository.findById(idTarjeta)
                .orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));
        e.setCupoDisponible(newCupoDisponible);
        e.setCupoUtilizado(e.getCupoTotal().subtract(newCupoDisponible));
        Card updated = repository.save(e);
        return toDTO(updated);
    }

    @Override
    @Transactional
    public CardDTO inactivateCard(Long idTarjeta) {
        Card e = repository.findById(idTarjeta)
                .orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));
        e.setEstado("Inactivo");
        Card updated = repository.save(e);
        return toDTO(updated);
    }
}
