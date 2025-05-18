package co.edu.unbosque.backend.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {
    private Long idTarjeta;
    private String numero;
    private String fechaVencimiento;
    private String franquicia;
    private String estado;
    private BigDecimal cupoTotal;
    private BigDecimal cupoDisponible;
    private BigDecimal cupoUtilizado;
    private Long idCliente;
}
