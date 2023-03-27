-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Erstellungszeit: 29. Sep 2020 um 15:51
-- Server-Version: 10.4.11-MariaDB
-- PHP-Version: 7.3.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `db_gamers_memory`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `gameList`
--

CREATE TABLE `gameList` (
  `gameId` int(11) NOT NULL,
  `officialName` text COLLATE utf8_german2_ci NOT NULL,
  `author` text COLLATE utf8_german2_ci DEFAULT NULL,
  `publisher` text COLLATE utf8_german2_ci DEFAULT NULL,
  `rating` int(11) NOT NULL,
  `estimation` text COLLATE utf8_german2_ci DEFAULT NULL,
  `whoHasIt` text COLLATE utf8_german2_ci NOT NULL,
  `recommendedAge` int(11) DEFAULT NULL,
  `idealAmountOfPlayers` text COLLATE utf8_german2_ci DEFAULT NULL,
  `recommendedAmountOfPlayers` text COLLATE utf8_german2_ci NOT NULL,
  `playingTime` text COLLATE utf8_german2_ci NOT NULL,
  `howOftenPlayed` text COLLATE utf8_german2_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `gameList`
--

INSERT INTO `gameList` (`gameId`, `officialName`, `author`, `publisher`, `rating`, `estimation`, `whoHasIt`, `recommendedAge`, `idealAmountOfPlayers`, `recommendedAmountOfPlayers`, `playingTime`, `howOftenPlayed`) VALUES
(230, 'Offizieller Name:* 1', 'Autor:1', 'Verlagsname: 1', 2, 'eigene Einschätzung und Meinung:1\neigene Einschätzung und Meinung:1', 'Messe,Andere', 12, '2,3', '1,2,3', 'Spieldauer:* 1', 'Wie oft gespielt? 1'),
(231, 'Offizieller Name:* 2', 'Autor:2', 'Verlagsname: 2', 3, 'eigene Einschätzung und Meinung:2\neigene Einschätzung und Meinung:2', 'Messe,Andere', 10, '2,3', '1,2,3', 'Spieldauer:* 2', 'Wie oft gespielt? 2'),
(232, 'Offizieller Name:* 3', 'Autor:3', 'Verlagsname: 3', 4, 'eigene Einschätzung und Meinung:3\neigene Einschätzung und Meinung:3', 'Chris,Familie,Messe,Andere', 8, '', '1', 'Spieldauer:* 3', 'Wie oft gespielt? 3'),
(233, 'Offizieller Name:* 4', 'Autor:4', 'Verlagsname: 4', 5, 'eigene Einschätzung und Meinung:4\neigene Einschätzung und Meinung:4', 'Freundeskeis,Spieleverein,Messe,Andere', 7, '2,3', '1,2,3', 'Spieldauer:* 4', 'Wie oft gespielt? 4'),
(234, 'Offizieller Name:* 5', 'Autor:5', 'Verlagsname: 5', 1, 'eigene Einschätzung und Meinung:5\neigene Einschätzung und Meinung:5', 'Freundeskeis,Messe,Andere', 6, '3', '3,4,5,6', 'Spieldauer:* 5', 'Wie oft gespielt? 5'),
(235, 'Offizieller Name:* 6', 'Autor:6', 'Verlagsname: 6', 2, 'eigene Einschätzung und Meinung:6\neigene Einschätzung und Meinung:6', 'Messe,Andere', 4, '2,3', '1,2,3', 'Spieldauer:* 6', 'Wie oft gespielt? 6'),
(240, 'Test 123', '', '', 4, '', 'Freundeskeis,Spieleverein,Messe', 8, '3,4', '3,4,5,6', 'fghdrth', '');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `gameList`
--
ALTER TABLE `gameList`
  ADD PRIMARY KEY (`gameId`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `gameList`
--
ALTER TABLE `gameList`
  MODIFY `gameId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=241;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
