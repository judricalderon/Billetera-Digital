package co.edu.unbosque.backend.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long idCliente;
    private String identificacion;
    private String nombreCompleto;
    private String correo;
    private String estado;
    private LocalDateTime fechaRegistro;
}
