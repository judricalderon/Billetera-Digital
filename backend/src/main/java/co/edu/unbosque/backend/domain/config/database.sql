-- 1. Crear la base de datos
CREATE DATABASE IF NOT EXISTS billetera_digital;
USE billetera_digital;

-- 2. Tabla de usuarios
CREATE TABLE users (
  id_cliente BIGINT AUTO_INCREMENT PRIMARY KEY,
  identificacion VARCHAR(50) NOT NULL UNIQUE,
  nombre_completo VARCHAR(100) NOT NULL,
  correo VARCHAR(100) NOT NULL UNIQUE,
  estado VARCHAR(20) NOT NULL,
  fecha_registro DATETIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3. Tabla de tarjetas
CREATE TABLE cards (
  id_tarjeta BIGINT AUTO_INCREMENT PRIMARY KEY,
  numero VARCHAR(16) NOT NULL,
  fecha_vencimiento VARCHAR(7) NOT NULL,
  franquicia VARCHAR(20) NOT NULL,
  estado VARCHAR(20) NOT NULL,
  cupo_total DECIMAL(15,2) NOT NULL,
  cupo_disponible DECIMAL(15,2) NOT NULL,
  cupo_utilizado DECIMAL(15,2) NOT NULL,
  id_cliente BIGINT NOT NULL,
  CONSTRAINT fk_cards_user
    FOREIGN KEY (id_cliente)
    REFERENCES users(id_cliente)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
