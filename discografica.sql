DROP DATABASE IF EXISTS discografica;
CREATE DATABASE discografica;
USE discografica;

-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-01-2019 a las 03:35:46
-- Versión del servidor: 10.1.35-MariaDB
-- Versión de PHP: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `discografica`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `album`
--

CREATE TABLE `album` (
  `ID` int(10) NOT NULL,
  `titulo` varchar(120) NOT NULL,
  `autor` varchar(120) NOT NULL,
  `publicacion` year(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `album`
--

INSERT INTO `album` (`ID`, `titulo`, `autor`, `publicacion`) VALUES
(1, 'Full Moon', 'Sonata Arctica', 2000),
(2, 'Black and Blue', 'Backstreet Boys', 2001),
(3, 'Nueva Era', 'Amistades Peligrosas', 1996),
(4, 'Descanso Dominical', 'Mecano', 1984),
(5, 'Backstreet Back', 'Backstreet Boys', 1997),
(6, 'Ana, Jose y Nacho', 'Mecano', 1986);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cancion`
--

CREATE TABLE `cancion` (
  `ID` int(10) NOT NULL,
  `titulo` varchar(120) NOT NULL,
  `duracion` varchar(120) DEFAULT NULL,
  `letras` varchar(120) DEFAULT NULL,
  `album` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cancion`
--

INSERT INTO `cancion` (`ID`, `titulo`, `duracion`, `letras`, `album`) VALUES
(1, 'Full Moon', '4:30', 'sdgadgagsgsdgsgdssgsg', 1),
(2, 'Shamandalie', '3:40', 'dggsdgsdgsa', 1),
(3, 'Incomplete', '5:90', 'gsdgsag', 2),
(4, 'Climbing the Wall', '2:20', 'sadgsgs', 2),
(5, 'Estoy Por Ti', '3:10', 'sdagadga', 3),
(6, 'Muy Peligroso', '4:20', 'sdgagdsa', 3),
(7, 'Mujer Contra Mujer', '4:10', 'sadgdsg', 4),
(8, 'Me Cole en una Fiesta', '3:20', 'sagsggsdag', 4);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `album`
--
ALTER TABLE `album`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `cancion`
--
ALTER TABLE `cancion`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `fk` (`album`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cancion`
--
ALTER TABLE `cancion`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cancion`
--
ALTER TABLE `cancion`
  ADD CONSTRAINT `fk` FOREIGN KEY (`album`) REFERENCES `album` (`ID`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
