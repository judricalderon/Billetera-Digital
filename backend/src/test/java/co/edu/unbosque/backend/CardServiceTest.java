package co.edu.unbosque.backend;
import co.edu.unbosque.backend.application.dtos.CardDTO;
import co.edu.unbosque.backend.application.services.CardService;
import co.edu.unbosque.backend.domain.interfaces.ICardRepository;
import co.edu.unbosque.backend.domain.interfaces.IUserRepository;
import co.edu.unbosque.backend.domain.model.Card;
import co.edu.unbosque.backend.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {
    @Mock
    private ICardRepository cardRepository;
    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private CardService cardService;

    private User user;
    private Card card1;
    private Card card2;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setIdCliente(10L);

        card1 = new Card();
        card1.setIdTarjeta(1L);
        card1.setNumero("1111222233334444");
        card1.setFechaVencimiento("12/2025");
        card1.setFranquicia("VISA");
        card1.setEstado("Activo");
        card1.setCupoTotal(new BigDecimal("1000.00"));
        card1.setCupoDisponible(new BigDecimal("800.00"));
        card1.setCupoUtilizado(new BigDecimal("200.00"));
        card1.setUser(user);

        card2 = new Card();
        card2.setIdTarjeta(2L);
        card2.setNumero("5555666677778888");
        card2.setFechaVencimiento("01/2026");
        card2.setFranquicia("MASTERCARD");
        card2.setEstado("Activo");
        card2.setCupoTotal(new BigDecimal("500.00"));
        card2.setCupoDisponible(new BigDecimal("500.00"));
        card2.setCupoUtilizado(BigDecimal.ZERO);
        card2.setUser(user);
    }

    @Test
    void getCardsByUser_shouldReturnDtoList() {
        when(cardRepository.findByUserId(10L))
                .thenReturn(Arrays.asList(card1, card2));

        List<CardDTO> dtos = cardService.getCardsByUser(10L);

        assertEquals(2, dtos.size());
        assertEquals(card1.getIdTarjeta(), dtos.get(0).getIdTarjeta());
        assertEquals(card2.getNumero(), dtos.get(1).getNumero());
        verify(cardRepository, times(1)).findByUserId(10L);
    }

    @Test
    void addCard_whenUserExists_shouldReturnDto() {
        CardDTO input = new CardDTO(
                null,
                "9999000011112222",
                "02/2027",
                "VISA",
                null,
                new BigDecimal("1200.00"),
                new BigDecimal("1200.00"),
                BigDecimal.ZERO,
                10L
        );
        Card toSave = new Card();
        toSave.setNumero(input.getNumero());
        toSave.setFechaVencimiento(input.getFechaVencimiento());
        toSave.setFranquicia(input.getFranquicia());
        toSave.setEstado("Activo");
        toSave.setCupoTotal(input.getCupoTotal());
        toSave.setCupoDisponible(input.getCupoDisponible());
        toSave.setCupoUtilizado(input.getCupoUtilizado());
        toSave.setUser(user);

        Card saved = new Card();
        saved.setIdTarjeta(5L);
        saved.setNumero(toSave.getNumero());
        saved.setFechaVencimiento(toSave.getFechaVencimiento());
        saved.setFranquicia(toSave.getFranquicia());
        saved.setEstado("Activo");
        saved.setCupoTotal(toSave.getCupoTotal());
        saved.setCupoDisponible(toSave.getCupoDisponible());
        saved.setCupoUtilizado(toSave.getCupoUtilizado());
        saved.setUser(user);

        when(userRepository.findById(10L)).thenReturn(Optional.of(user));
        when(cardRepository.save(any(Card.class))).thenReturn(saved);

        CardDTO result = cardService.addCard(input);

        assertEquals(5L, result.getIdTarjeta());
        assertEquals("9999000011112222", result.getNumero());
        assertEquals("Activo", result.getEstado());
        assertEquals(input.getCupoTotal(), result.getCupoTotal());
        verify(userRepository).findById(10L);
        verify(cardRepository).save(any(Card.class));
    }

    @Test
    void addCard_whenUserNotFound_shouldThrow() {
        when(userRepository.findById(10L)).thenReturn(Optional.empty());
        CardDTO input = new CardDTO();
        input.setIdCliente(10L);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> cardService.addCard(input)
        );
        assertEquals("Usuario no encontrado", ex.getMessage());
    }

    @Test
    void updateCardCupo_whenCardExists_shouldReturnUpdatedDto() {
        when(cardRepository.findById(1L)).thenReturn(Optional.of(card1));
        BigDecimal nuevo = new BigDecimal("500.00");

        Card updatedEntity = new Card();
        updatedEntity.setIdTarjeta(1L);
        updatedEntity.setNumero(card1.getNumero());
        updatedEntity.setFechaVencimiento(card1.getFechaVencimiento());
        updatedEntity.setFranquicia(card1.getFranquicia());
        updatedEntity.setEstado(card1.getEstado());
        updatedEntity.setCupoTotal(card1.getCupoTotal());
        updatedEntity.setCupoDisponible(nuevo);
        updatedEntity.setCupoUtilizado(card1.getCupoTotal().subtract(nuevo));
        updatedEntity.setUser(user);

        when(cardRepository.save(any(Card.class))).thenReturn(updatedEntity);

        CardDTO result = cardService.updateCardCupo(1L, nuevo);

        assertEquals(nuevo, result.getCupoDisponible());
        assertEquals(card1.getCupoTotal().subtract(nuevo), result.getCupoUtilizado());
        verify(cardRepository).findById(1L);
        verify(cardRepository).save(any(Card.class));
    }

    @Test
    void updateCardCupo_whenNotFound_shouldThrow() {
        when(cardRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class,
                () -> cardService.updateCardCupo(99L, BigDecimal.ONE)
        );
    }

    @Test
    void inactivateCard_whenCardExists_shouldReturnDtoWithInactiveState() {
        when(cardRepository.findById(2L)).thenReturn(Optional.of(card2));
        Card inactivated = new Card();
        inactivated.setIdTarjeta(2L);
        inactivated.setNumero(card2.getNumero());
        inactivated.setFechaVencimiento(card2.getFechaVencimiento());
        inactivated.setFranquicia(card2.getFranquicia());
        inactivated.setEstado("Inactivo");
        inactivated.setCupoTotal(card2.getCupoTotal());
        inactivated.setCupoDisponible(card2.getCupoDisponible());
        inactivated.setCupoUtilizado(card2.getCupoUtilizado());
        inactivated.setUser(user);

        when(cardRepository.save(any(Card.class))).thenReturn(inactivated);

        CardDTO result = cardService.inactivateCard(2L);

        assertEquals("Inactivo", result.getEstado());
        verify(cardRepository).findById(2L);
        verify(cardRepository).save(any(Card.class));
    }

    @Test
    void inactivateCard_whenNotFound_shouldThrow() {
        when(cardRepository.findById(42L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class,
                () -> cardService.inactivateCard(42L)
        );
    }


}
