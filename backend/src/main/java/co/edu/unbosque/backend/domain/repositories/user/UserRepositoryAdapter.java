package co.edu.unbosque.backend.domain.repositories.user;

import co.edu.unbosque.backend.domain.interfaces.IUserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryAdapter implements IUserRepository {
    private final SpringDataUserRepository jpa;

    public UserRepositoryAdapter(SpringDataUserRepository jpa) {
        this.jpa = jpa;
    }
}
