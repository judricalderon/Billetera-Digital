package co.edu.unbosque.backend.application.tests;

import co.edu.unbosque.backend.application.dtos.UserDTO;
import co.edu.unbosque.backend.application.services.UserService;
import co.edu.unbosque.backend.domain.interfaces.IUserRepository;
import co.edu.unbosque.backend.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        user1 = new User();
        user1.setIdCliente(1L);
        user1.setIdentificacion("1001");
        user1.setNombreCompleto("Alice");
        user1.setCorreo("alice@example.com");
        user1.setEstado("Activo");
        user1.setFechaRegistro(LocalDateTime.of(2025,5,18,10,0));

        user2 = new User();
        user2.setIdCliente(2L);
        user2.setIdentificacion("1002");
        user2.setNombreCompleto("Bob");
        user2.setCorreo("bob@example.com");
        user2.setEstado("Inactivo");
        user2.setFechaRegistro(LocalDateTime.of(2025,5,17,15,30));
    }

    @Test
    void getAllUsers_returnsDtoList() {
        // Arrange
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        // Act
        List<UserDTO> dtos = userService.getAllUsers();

        // Assert
        assertEquals(2, dtos.size());

        UserDTO dto1 = dtos.get(0);
        assertEquals(1L, dto1.getIdCliente());
        assertEquals("Alice", dto1.getNombreCompleto());
        assertEquals("alice@example.com", dto1.getCorreo());
        assertEquals("Activo", dto1.getEstado());

        UserDTO dto2 = dtos.get(1);
        assertEquals(2L, dto2.getIdCliente());
        assertEquals("Bob", dto2.getNombreCompleto());
        assertEquals("bob@example.com", dto2.getCorreo());
        assertEquals("Inactivo", dto2.getEstado());

        verify(userRepository, times(1)).findAll();
    }
}
