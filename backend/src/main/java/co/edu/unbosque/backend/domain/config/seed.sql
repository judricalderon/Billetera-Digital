-- Inserta usuarios de prueba
INSERT INTO users (
  identificacion,
  nombre_completo,
  correo,
  estado,
  fecha_registro
) VALUES
  ('1020304050', 'Juan Pérez', 'juan.perez@example.com', 'Activo', NOW()),
  ('2030405060', 'María García', 'maria.garcia@example.com', 'Activo', NOW()),
  ('3040506070', 'Carlos Rodríguez', 'carlos.rodriguez@example.com', 'Activo', NOW());
