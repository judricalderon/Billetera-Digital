package co.edu.unbosque.backend.domain.repositories.user;

import co.edu.unbosque.backend.domain.interfaces.IUserRepository;
import co.edu.unbosque.backend.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryAdapter implements IUserRepository {
    private final SpringDataUserRepository jpa;

    public UserRepositoryAdapter(SpringDataUserRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Optional<User> findById(Long idCliente) {
        return jpa.findById(idCliente);
    }

    @Override
    public List<User> findAll() {
        return jpa.findAll();
    }

    @Override
    public User save(User user) {
        return jpa.save(user);
    }
}
