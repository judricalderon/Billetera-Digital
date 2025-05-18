package co.edu.unbosque.backend.application.interfaces;

import co.edu.unbosque.backend.application.dtos.UserDTO;
import co.edu.unbosque.backend.domain.model.User;

import java.util.List;

public interface IUserService {
    List<UserDTO> getAllUsers();
    UserDTO createUser(UserDTO userDTO);
}
