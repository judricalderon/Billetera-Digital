package co.edu.unbosque.backend.domain.interfaces;

import co.edu.unbosque.backend.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    Optional<User> findById(Long idCliente);
    List<User> findAll();
    User save(User userEntity);
}
