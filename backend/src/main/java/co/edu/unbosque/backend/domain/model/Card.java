package co.edu.unbosque.backend.domain.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarjeta;

    @Column(nullable = false, length = 16)
    private String numero;

    @Column(nullable = false, length = 7) // "MM/YYYY"
    private String fechaVencimiento;

    @Column(nullable = false, length = 20)
    private String franquicia;

    @Column(nullable = false, length = 20)
    private String estado;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal cupoTotal;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal cupoDisponible;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal cupoUtilizado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private User user;

    public Long getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(Long idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getFranquicia() {
        return franquicia;
    }

    public void setFranquicia(String franquicia) {
        this.franquicia = franquicia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getCupoTotal() {
        return cupoTotal;
    }

    public void setCupoTotal(BigDecimal cupoTotal) {
        this.cupoTotal = cupoTotal;
    }

    public BigDecimal getCupoDisponible() {
        return cupoDisponible;
    }

    public void setCupoDisponible(BigDecimal cupoDisponible) {
        this.cupoDisponible = cupoDisponible;
    }

    public BigDecimal getCupoUtilizado() {
        return cupoUtilizado;
    }

    public void setCupoUtilizado(BigDecimal cupoUtilizado) {
        this.cupoUtilizado = cupoUtilizado;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
