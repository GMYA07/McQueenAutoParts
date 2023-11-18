-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-11-2023 a las 21:47:06
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mcqueenautoparts`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administradores`
--

CREATE TABLE `administradores` (
  `idAdmin` int(11) NOT NULL,
  `usuario` varchar(25) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `pass` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `administradores`
--

INSERT INTO `administradores` (`idAdmin`, `usuario`, `correo`, `pass`) VALUES
(1, 'yael.matamoros', 'yaelguzman750@gmail.com', 'guzman'),
(2, 'miguel.sanchez', 'miguesanche1203@gmail.com', 'sanchez'),
(5, 'angel.pimentel', 'pimentel202012@gmail.com', 'pimentel'),
(6, 'cristian.santos', 'cristianalexandersantos0426@gmail.com', 'santos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `automoviles`
--

CREATE TABLE `automoviles` (
  `idAutomovil` varchar(6) NOT NULL,
  `placaAutomovil` varchar(8) DEFAULT NULL,
  `marcaAutomovil` varchar(30) NOT NULL,
  `modeloAutomovil` varchar(30) NOT NULL,
  `yearAutomovil` int(5) NOT NULL,
  `especificaciones` varchar(100) NOT NULL,
  `colorAutomovil` varchar(30) NOT NULL,
  `precioAutomovil` float NOT NULL,
  `fotoAutomovil` varchar(50) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `idClienteVenta` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `automoviles`
--

INSERT INTO `automoviles` (`idAutomovil`, `placaAutomovil`, `marcaAutomovil`, `modeloAutomovil`, `yearAutomovil`, `especificaciones`, `colorAutomovil`, `precioAutomovil`, `fotoAutomovil`, `cantidad`, `estado`, `idClienteVenta`) VALUES
('ATA141', NULL, 'XD7', 'XD7', 1234, 'XD7', 'XD7', 7777, 'siuuu.jpg', 0, 12, NULL),
('ATA175', NULL, 'XD', 'XD', 2444, 'XD', 'XD', 20000, 'vini.jpg', 0, 12, NULL),
('ATA316', NULL, 'XD6', 'XD6', 12345, 'XD6', 'XD6', 23451, 'bellingham-celebracion-real-madrid.jpg', 12354, 11, NULL),
('ATA748', NULL, 'Nissan', 'Sentraxd', 5, 'xd', 'Rojo', 0, 'siuuu.jpg', 25, 12, NULL),
('ATA904', NULL, 'XD', 'XD', 12312, 'XD', 'XD', 12321, 'Cristiano.jpg', 1, 11, NULL),
('ATR818', '12345678', 'Ferrrari', 'M7', 2003, 'TA NAZI', 'ROJO', 600, 'Cristiano.jpg', NULL, 30, NULL),
('ATS190', '555-445', 'BMW', 'GOD', 2020, 'Ta very nazi', 'Rojillo', 70000, 'Cristiano.jpg', NULL, 4, '1234567-7'),
('ATS422', '555-445', 'NIssan', 'GOD', 2026, 'xd', 'Nigger', 40000, 'vini.jpg', NULL, 3, '1234567-7');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrito`
--

CREATE TABLE `carrito` (
  `idCarrito` int(11) NOT NULL,
  `idProducto` varchar(6) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `citasmec`
--

CREATE TABLE `citasmec` (
  `idCita` int(11) NOT NULL,
  `descripcionCita` varchar(100) NOT NULL,
  `estadoCita` int(11) NOT NULL,
  `idMecanico` varchar(10) NOT NULL,
  `idCliente` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `citasmec`
--

INSERT INTO `citasmec` (`idCita`, `descripcionCita`, `estadoCita`, `idMecanico`, `idCliente`) VALUES
(3, 'XD', 52, '12345678-9', '1234567-7'),
(6, '1234', 52, '12345678-9', '1234567-7'),
(7, 'xd', 52, '12345678-9', '1234567-7'),
(8, 'auch', 52, '12345678-9', '1234567-7');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `dui` varchar(10) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `pass` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`dui`, `nombre`, `correo`, `pass`) VALUES
('12345678-5', 'MariaAntonieta', 'yaelguzman750@gmail.com4', 'asd');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mecanicos`
--

CREATE TABLE `mecanicos` (
  `dui` varchar(10) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `pass` varchar(25) NOT NULL,
  `estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mecanicos`
--

INSERT INTO `mecanicos` (`dui`, `nombre`, `correo`, `pass`, `estado`) VALUES
('12345678-9', 'Antonio', 'cr7@gmail.com', 'AAA', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rentas`
--

CREATE TABLE `rentas` (
  `idRenta` varchar(6) NOT NULL,
  `idCarro` varchar(6) NOT NULL,
  `idCliente` varchar(10) NOT NULL,
  `FechaInicio` varchar(50) NOT NULL,
  `FechaFinal` varchar(50) NOT NULL,
  `precioRenta` int(11) NOT NULL,
  `estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rentas`
--

INSERT INTO `rentas` (`idRenta`, `idCarro`, `idCliente`, `FechaInicio`, `FechaFinal`, `precioRenta`, `estado`) VALUES
('RTA769', 'ATR818', '1234567-7', '09/11/2023', '17/11/2023', 4800, 43);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `repuestos`
--

CREATE TABLE `repuestos` (
  `idRepuesto` varchar(6) NOT NULL,
  `categorias` varchar(30) NOT NULL,
  `imagenRepuesto` varchar(50) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `precio` float NOT NULL,
  `cantidad` int(11) NOT NULL,
  `marca` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `repuestos`
--

INSERT INTO `repuestos` (`idRepuesto`, `categorias`, `imagenRepuesto`, `nombre`, `precio`, `cantidad`, `marca`) VALUES
('RPT221', 'Sistema de frenos', 'vini.jpg', 'Llantas xd', 12, 0, 'xd'),
('RPT397', 'Partes de motor', 'siuuu.jpg', 'Llantas', 12, 10, 'xd');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `dui` varchar(10) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `pass` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`dui`, `telefono`, `nombre`, `correo`, `pass`) VALUES
('1234567-7', '7988-2404', 'Cristian Santos', 'yaelguzman750@gmail.com', 'guzmana');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventasauto`
--

CREATE TABLE `ventasauto` (
  `idVenta` varchar(6) NOT NULL,
  `idCarro` varchar(10) NOT NULL,
  `idCliente` varchar(10) NOT NULL,
  `estado` int(11) NOT NULL,
  `precio` int(11) NOT NULL,
  `mensajeVenta` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventasrepuestos`
--

CREATE TABLE `ventasrepuestos` (
  `idVentaRepuesto` varchar(6) NOT NULL,
  `idCliente` varchar(10) NOT NULL,
  `precioVentaRep` int(11) NOT NULL,
  `estadoVenta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ventasrepuestos`
--

INSERT INTO `ventasrepuestos` (`idVentaRepuesto`, `idCliente`, `precioVentaRep`, `estadoVenta`) VALUES
('VTR400', '1234567-7', 24, 62),
('VTR595', '1234567-7', 24, 61);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administradores`
--
ALTER TABLE `administradores`
  ADD PRIMARY KEY (`idAdmin`);

--
-- Indices de la tabla `automoviles`
--
ALTER TABLE `automoviles`
  ADD PRIMARY KEY (`idAutomovil`),
  ADD KEY `idClienteVenta` (`idClienteVenta`);

--
-- Indices de la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD PRIMARY KEY (`idCarrito`),
  ADD KEY `idProducto` (`idProducto`);

--
-- Indices de la tabla `citasmec`
--
ALTER TABLE `citasmec`
  ADD PRIMARY KEY (`idCita`),
  ADD KEY `idMecanico` (`idMecanico`),
  ADD KEY `idCliente` (`idCliente`) USING BTREE;

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`dui`);

--
-- Indices de la tabla `mecanicos`
--
ALTER TABLE `mecanicos`
  ADD PRIMARY KEY (`dui`);

--
-- Indices de la tabla `rentas`
--
ALTER TABLE `rentas`
  ADD PRIMARY KEY (`idRenta`),
  ADD KEY `idCarro` (`idCarro`),
  ADD KEY `idCliente` (`idCliente`);

--
-- Indices de la tabla `repuestos`
--
ALTER TABLE `repuestos`
  ADD PRIMARY KEY (`idRepuesto`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`dui`);

--
-- Indices de la tabla `ventasauto`
--
ALTER TABLE `ventasauto`
  ADD PRIMARY KEY (`idVenta`),
  ADD KEY `idCarro` (`idCarro`),
  ADD KEY `idCliente` (`idCliente`);

--
-- Indices de la tabla `ventasrepuestos`
--
ALTER TABLE `ventasrepuestos`
  ADD PRIMARY KEY (`idVentaRepuesto`),
  ADD KEY `idCliente` (`idCliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `administradores`
--
ALTER TABLE `administradores`
  MODIFY `idAdmin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `carrito`
--
ALTER TABLE `carrito`
  MODIFY `idCarrito` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT de la tabla `citasmec`
--
ALTER TABLE `citasmec`
  MODIFY `idCita` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `automoviles`
--
ALTER TABLE `automoviles`
  ADD CONSTRAINT `automoviles_ibfk_1` FOREIGN KEY (`idClienteVenta`) REFERENCES `usuario` (`dui`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD CONSTRAINT `carrito_ibfk_1` FOREIGN KEY (`idProducto`) REFERENCES `repuestos` (`idRepuesto`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `citasmec`
--
ALTER TABLE `citasmec`
  ADD CONSTRAINT `citasmec_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `usuario` (`dui`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `citasmec_ibfk_2` FOREIGN KEY (`idMecanico`) REFERENCES `mecanicos` (`dui`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `rentas`
--
ALTER TABLE `rentas`
  ADD CONSTRAINT `rentas_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `usuario` (`dui`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `rentas_ibfk_2` FOREIGN KEY (`idCarro`) REFERENCES `automoviles` (`idAutomovil`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `ventasauto`
--
ALTER TABLE `ventasauto`
  ADD CONSTRAINT `ventasauto_ibfk_1` FOREIGN KEY (`idCarro`) REFERENCES `automoviles` (`idAutomovil`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ventasauto_ibfk_2` FOREIGN KEY (`idCliente`) REFERENCES `usuario` (`dui`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `ventasrepuestos`
--
ALTER TABLE `ventasrepuestos`
  ADD CONSTRAINT `ventasrepuestos_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `usuario` (`dui`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
