package co.edu.unbosque.backend.application.services;

import co.edu.unbosque.backend.application.dtos.UserDTO;
import co.edu.unbosque.backend.application.interfaces.IUserService;
import co.edu.unbosque.backend.domain.interfaces.IUserRepository;
import co.edu.unbosque.backend.domain.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    private final IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    private UserDTO toDTO(User e) {
        return new UserDTO(
                e.getIdCliente(),
                e.getIdentificacion(),
                e.getNombreCompleto(),
                e.getCorreo(),
                e.getEstado()
        );
    }

    private User toEntity(UserDTO d) {
        User e = new User();
        e.setIdCliente(d.getIdCliente());
        e.setIdentificacion(d.getIdentificacion());
        e.setNombreCompleto(d.getNombreCompleto());
        e.setCorreo(d.getCorreo());
        e.setEstado(d.getEstado());
        e.setFechaRegistro(LocalDateTime.now());
        return e;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        User saved = repository.save(toEntity(userDTO));
        return toDTO(saved);
    }
}
