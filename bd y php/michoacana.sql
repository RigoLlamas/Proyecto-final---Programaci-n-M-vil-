-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-12-2023 a las 01:52:21
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `michoacana`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `sabor` varchar(50) DEFAULT NULL,
  `tamano` varchar(50) DEFAULT NULL,
  `cantidad` varchar(3) DEFAULT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `domicilio` varchar(50) DEFAULT NULL,
  `hora` varchar(6) DEFAULT NULL,
  `fecha` varchar(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id`, `nombre`, `sabor`, `tamano`, `cantidad`, `telefono`, `domicilio`, `hora`, `fecha`) VALUES
(1, 'Rigo', 'Cafe', 'Grande', '2', '3314595040', 'C', '04:09', '2100 / 12 / 09'),
(4, 'Jesus', 'Frutos Rojos', 'Mediana', '2', '3385855858', 'Oblatos', '05:24', '2100 / 12 / 31'),
(5, 'Paco', 'Pipian', 'Grande', '3', '3312211221', 'Mi casa', '05:31', '2191 / 08 / 05'),
(7, 'Tere', 'Limon', 'Mediana', '3', '3358858585', 'Tonala', '06:13', '2100 / 09 / 05'),
(8, 'Rita', 'Jamaica', 'Mediana', '3', '3358858585', 'Tonala', '21:20', '2100 / 12 / 20');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nombre_usuario` varchar(50) DEFAULT NULL,
  `contrasena` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre_usuario`, `contrasena`) VALUES
(1, 'Juan', 'Juan'),
(2, 'Maria', 'Maria'),
(3, 'Pedro', 'Pedro'),
(4, 'Ana', 'Ana'),
(5, 'Luis', 'Luis');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
