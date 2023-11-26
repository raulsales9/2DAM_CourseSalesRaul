-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: mariadbAd
-- Tiempo de generación: 26-11-2023 a las 19:14:02
-- Versión del servidor: 11.1.2-MariaDB-1:11.1.2+maria~ubu2204
-- Versión de PHP: 8.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `kurigram`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Conversation`
--

CREATE TABLE `Conversation` (
  `id` int(11) NOT NULL,
  `last_message_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `conversation_user`
--

CREATE TABLE `conversation_user` (
  `conversation_id` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Event`
--

CREATE TABLE `Event` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `place` varchar(50) DEFAULT NULL,
  `start_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Follow`
--

CREATE TABLE `Follow` (
  `id` int(11) NOT NULL,
  `followers` int(11) DEFAULT NULL,
  `following` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Message`
--

CREATE TABLE `Message` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `conversation_id` int(11) DEFAULT NULL,
  `iduser` bigint(20) DEFAULT NULL,
  `sender_iduser` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Participant`
--

CREATE TABLE `Participant` (
  `id` int(11) NOT NULL,
  `conversation_id` int(11) DEFAULT NULL,
  `iduser` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Post`
--

CREATE TABLE `Post` (
  `idPost` int(11) NOT NULL,
  `created_at` date DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `isSubmitted` bit(1) DEFAULT NULL,
  `likes` int(11) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `id_user` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `Post`
--

INSERT INTO `Post` (`idPost`, `created_at`, `image`, `isSubmitted`, `likes`, `text`, `title`, `id_user`) VALUES
(1, NULL, NULL, NULL, NULL, NULL, 'rewfr', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Profile`
--

CREATE TABLE `Profile` (
  `id` bigint(20) NOT NULL,
  `bio` varchar(50) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `User`
--

CREATE TABLE `User` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `User`
--

INSERT INTO `User` (`id`, `email`, `name`, `password`, `phone`) VALUES
(1, 'raul@gmail.com', 'raul', 'raul', 644435465),
(2, 'user1@example.com', 'User 1', 'password1', 1234567891),
(3, 'user2@example.com', 'User 2', 'password2', 1234567892),
(4, 'user3@example.com', 'User 3', 'password3', 1234567893),
(5, 'user4@example.com', 'User 4', 'password4', 1234567894),
(6, 'user5@example.com', 'User 5', 'password5', 1234567895),
(7, 'user6@example.com', 'User 6', 'password6', 1234567896),
(8, 'user7@example.com', 'User 7', 'password7', 1234567897),
(9, 'user8@example.com', 'User 8', 'password8', 1234567898),
(10, 'user9@example.com', 'User 9', 'password9', 1234567899),
(11, 'user10@example.com', 'User 10', 'password10', 1234567810),
(12, 'user11@example.com', 'User 11', 'password11', 1234567811),
(13, 'user12@example.com', 'User 12', 'password12', 1234567812),
(14, 'user13@example.com', 'User 13', 'password13', 1234567813),
(15, 'user14@example.com', 'User 14', 'password14', 1234567814),
(16, 'user15@example.com', 'User 15', 'password15', 1234567815),
(17, 'user16@example.com', 'User 16', 'password16', 1234567816),
(18, 'user17@example.com', 'User 17', 'password17', 1234567817),
(19, 'user18@example.com', 'User 18', 'password18', 1234567818),
(20, 'user19@example.com', 'User 19', 'password19', 1234567819),
(21, 'user20@example.com', 'User 20', 'password20', 1234567820),
(22, 'user21@example.com', 'User 21', 'password21', 1234567821),
(23, 'user22@example.com', 'User 22', 'password22', 1234567822),
(24, 'user23@example.com', 'User 23', 'password23', 1234567823),
(25, 'user24@example.com', 'User 24', 'password24', 1234567824),
(26, 'user25@example.com', 'User 25', 'password25', 1234567825),
(27, 'user26@example.com', 'User 26', 'password26', 1234567826),
(28, 'user27@example.com', 'User 27', 'password27', 1234567827),
(29, 'user28@example.com', 'User 28', 'password28', 1234567828),
(30, 'user29@example.com', 'User 29', 'password29', 1234567829),
(31, 'user30@example.com', 'User 30', 'password30', 1234567830);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `User_Event`
--

CREATE TABLE `User_Event` (
  `user_id` bigint(20) NOT NULL,
  `event_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `User_Follow`
--

CREATE TABLE `User_Follow` (
  `user_id` int(11) NOT NULL,
  `follow_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Conversation`
--
ALTER TABLE `Conversation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6j2uwdcuegp60dfexyi8dquto` (`last_message_id`);

--
-- Indices de la tabla `conversation_user`
--
ALTER TABLE `conversation_user`
  ADD PRIMARY KEY (`conversation_id`,`user_id`),
  ADD KEY `FK7a8r5xrna8m6olvhkj8gpbea7` (`user_id`);

--
-- Indices de la tabla `Event`
--
ALTER TABLE `Event`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Follow`
--
ALTER TABLE `Follow`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Message`
--
ALTER TABLE `Message`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9g43ca4we31vtskksn4ml32j1` (`conversation_id`),
  ADD KEY `FK2fh0dik0k65vcr2uixm7826lp` (`iduser`),
  ADD KEY `FKal5cbl19qmrxxmpvoehju3ppy` (`sender_iduser`);

--
-- Indices de la tabla `Participant`
--
ALTER TABLE `Participant`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKg490jd3tl9gq50w4bd58xg1b7` (`conversation_id`),
  ADD KEY `FKetdg9f2cjv3kdg35am3flaw18` (`iduser`);

--
-- Indices de la tabla `Post`
--
ALTER TABLE `Post`
  ADD PRIMARY KEY (`idPost`),
  ADD KEY `FKnyty5aa9lpo3aen6l1l59o52m` (`id_user`);

--
-- Indices de la tabla `Profile`
--
ALTER TABLE `Profile`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9ke43amidbypdepae19k20a12` (`user_id`);

--
-- Indices de la tabla `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `User_Event`
--
ALTER TABLE `User_Event`
  ADD PRIMARY KEY (`user_id`,`event_id`),
  ADD KEY `FK454135j8pad8evd2c0uv8lylb` (`event_id`);

--
-- Indices de la tabla `User_Follow`
--
ALTER TABLE `User_Follow`
  ADD KEY `FKo2lcn923t7jpwcwr7mqfsxoj7` (`follow_id`),
  ADD KEY `FKeb0aw0xcuphmtmd7u02arc2` (`user_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Conversation`
--
ALTER TABLE `Conversation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `Participant`
--
ALTER TABLE `Participant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `Profile`
--
ALTER TABLE `Profile`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `User`
--
ALTER TABLE `User`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Conversation`
--
ALTER TABLE `Conversation`
  ADD CONSTRAINT `FK6j2uwdcuegp60dfexyi8dquto` FOREIGN KEY (`last_message_id`) REFERENCES `Message` (`id`);

--
-- Filtros para la tabla `conversation_user`
--
ALTER TABLE `conversation_user`
  ADD CONSTRAINT `FK7a8r5xrna8m6olvhkj8gpbea7` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  ADD CONSTRAINT `FKmq1p5lxdt9hkw8ner2ivlbsy3` FOREIGN KEY (`conversation_id`) REFERENCES `Conversation` (`id`);

--
-- Filtros para la tabla `Message`
--
ALTER TABLE `Message`
  ADD CONSTRAINT `FK2fh0dik0k65vcr2uixm7826lp` FOREIGN KEY (`iduser`) REFERENCES `User` (`id`),
  ADD CONSTRAINT `FK9g43ca4we31vtskksn4ml32j1` FOREIGN KEY (`conversation_id`) REFERENCES `Conversation` (`id`),
  ADD CONSTRAINT `FKal5cbl19qmrxxmpvoehju3ppy` FOREIGN KEY (`sender_iduser`) REFERENCES `User` (`id`);

--
-- Filtros para la tabla `Participant`
--
ALTER TABLE `Participant`
  ADD CONSTRAINT `FKetdg9f2cjv3kdg35am3flaw18` FOREIGN KEY (`iduser`) REFERENCES `User` (`id`),
  ADD CONSTRAINT `FKg490jd3tl9gq50w4bd58xg1b7` FOREIGN KEY (`conversation_id`) REFERENCES `Conversation` (`id`);

--
-- Filtros para la tabla `Post`
--
ALTER TABLE `Post`
  ADD CONSTRAINT `FKnyty5aa9lpo3aen6l1l59o52m` FOREIGN KEY (`id_user`) REFERENCES `User` (`id`);

--
-- Filtros para la tabla `Profile`
--
ALTER TABLE `Profile`
  ADD CONSTRAINT `FK9ke43amidbypdepae19k20a12` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`);

--
-- Filtros para la tabla `User_Event`
--
ALTER TABLE `User_Event`
  ADD CONSTRAINT `FK454135j8pad8evd2c0uv8lylb` FOREIGN KEY (`event_id`) REFERENCES `Event` (`id`),
  ADD CONSTRAINT `FKmyr6es39ymds0nd2etjbgxn6t` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`);

--
-- Filtros para la tabla `User_Follow`
--
ALTER TABLE `User_Follow`
  ADD CONSTRAINT `FKeb0aw0xcuphmtmd7u02arc2` FOREIGN KEY (`user_id`) REFERENCES `Follow` (`id`),
  ADD CONSTRAINT `FKo2lcn923t7jpwcwr7mqfsxoj7` FOREIGN KEY (`follow_id`) REFERENCES `User` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
