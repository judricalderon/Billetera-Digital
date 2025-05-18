package co.edu.unbosque.backend.domain.repositories.user;

import co.edu.unbosque.backend.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserRepository extends JpaRepository<User, Long> {}
