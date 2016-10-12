-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u1
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Lun 10 Octobre 2016 à 21:14
-- Version du serveur :  5.5.49-0+deb8u1
-- Version de PHP :  5.6.23-0+deb8u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `casier`
--

-- --------------------------------------------------------

--
-- Structure de la table `Bureau`
--

CREATE TABLE IF NOT EXISTS `Bureau` (
  `idBureau` int(10) NOT NULL,
  `description` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `Bureau`
--

INSERT INTO `Bureau` (`idBureau`, `description`) VALUES
(1, 'Administration'),
(2, 'Science');

-- --------------------------------------------------------

--
-- Structure de la table `bureauLinkage`
--

CREATE TABLE IF NOT EXISTS `bureauLinkage` (
  `idLink` int(10) NOT NULL,
  `idBureau` int(10) NOT NULL,
  `idPersonne` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `bureauLinkage`
--

INSERT INTO `bureauLinkage` (`idLink`, `idBureau`, `idPersonne`) VALUES
(2, 1, 2),
(3, 1, 3);

-- --------------------------------------------------------

--
-- Structure de la table `Personne`
--

CREATE TABLE IF NOT EXISTS `Personne` (
  `idPersonne` int(100) NOT NULL,
  `nom` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `telephone` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fonction` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `Personne`
--

INSERT INTO `Personne` (`idPersonne`, `nom`, `telephone`, `fonction`) VALUES
(1, 'bob', '0658991421', 'no function'),
(2, 'John', '00000000', 'no function'),
(3, 'jack', '00000000', 'chercheur');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `Bureau`
--
ALTER TABLE `Bureau`
 ADD PRIMARY KEY (`idBureau`);

--
-- Index pour la table `bureauLinkage`
--
ALTER TABLE `bureauLinkage`
 ADD PRIMARY KEY (`idLink`);

--
-- Index pour la table `Personne`
--
ALTER TABLE `Personne`
 ADD PRIMARY KEY (`idPersonne`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
