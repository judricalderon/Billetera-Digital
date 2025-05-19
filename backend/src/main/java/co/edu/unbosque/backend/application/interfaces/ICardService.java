package co.edu.unbosque.backend.application.interfaces;

import co.edu.unbosque.backend.application.dtos.CardDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ICardService {
    List<CardDTO> getCardsByUser(Long idCliente);
    CardDTO addCard(CardDTO cardDTO);
    CardDTO updateCardCupo(Long idTarjeta, BigDecimal newCupoDisponible);
    CardDTO inactivateCard(Long idTarjeta);
}
