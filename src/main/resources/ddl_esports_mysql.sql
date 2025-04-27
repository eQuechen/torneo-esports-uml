CREATE DATABASE IF NOT EXISTS `esports_mysql`;

USE `esports_mysql`;

CREATE TABLE `equipos` (
  `id_equipo` INT PRIMARY KEY AUTO_INCREMENT,
  `nombre_equipo` VARCHAR(100) NOT NULL,
  `pais_equipo` VARCHAR(100) NOT NULL
);

CREATE TABLE `jugadores` (
  `id_jugador` INT PRIMARY KEY AUTO_INCREMENT,
  `nombre_jugador` VARCHAR(100) NOT NULL,
  `apellidos_jugador` VARCHAR(100) NOT NULL,
  `id_equipo` INT,
  CONSTRAINT `fk_jugadores_equipos`
    FOREIGN KEY (`id_equipo`)
    REFERENCES `equipos` (`id_equipo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE
);

INSERT INTO `equipos` (nombre_equipo, pais_equipo) VALUES
  ('Tiburones','España'),
  ('Delfines', 'Argentina'),
  ('Pulpos', 'México'),
  ('Medusas', 'Chile'),
  ('Caballitos de mar', 'Colombia');

USE `esports_mysql`;

INSERT INTO `jugadores` (nombre_jugador, apellidos_jugador, id_equipo) VALUES

  ('Juan',   'Pérez',      1),
  ('María',  'García',     1),
  ('Luis',   'Hernández',  1),
  ('Ana',    'López',      1),
  ('Pedro',  'Martín',     1),

  ('Carlos', 'Martínez',   2),
  ('Lucía',  'Gómez',      2),
  ('Marco',  'Díaz',       2),
  ('Isabel', 'Sánchez',    2),
  ('Jorge',  'Romero',     2),

  ('Pedro',  'Torres',     3),
  ('Marta',  'Ruiz',       3),
  ('José',   'Ramírez',    3),
  ('Carmen', 'Flores',     3),
  ('Pablo',  'Castro',     3),

  ('Elena',  'Morales',    4),
  ('Diego',  'Ortiz',      4),
  ('Teresa', 'Navarro',    4),
  ('Alberto','Rojas',      4),
  ('Paula',  'Bravo',      4),

  ('Sergio', 'Molina',     5),
  ('Laura',  'Herrera',    5),
  ('Andrés', 'Vega',       5),
  ('Sandra', 'Silva',      5),
  ('Miguel', 'Campos',     5);
