package co.edu.unbosque.backend.domain.interfaces;

import co.edu.unbosque.backend.domain.model.User;

import java.util.List;

public interface IUserRepository {
    List<User> findAll();
    User save(User userEntity);
}
