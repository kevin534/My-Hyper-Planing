-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 16 jan. 2022 à 17:16
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `hyperplanning`
--

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

DROP TABLE IF EXISTS `cours`;
CREATE TABLE IF NOT EXISTS `cours` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `idEnseignants` int(4) NOT NULL,
  `codeSalle` int(4) NOT NULL,
  `groupeClasse` int(4) NOT NULL,
  `codeMatiere` int(4) NOT NULL,
  `dateDebut` varchar(25) NOT NULL,
  `dateFin` varchar(25) NOT NULL,
  `heureDebut` varchar(10) NOT NULL,
  PRIMARY KEY (`groupeClasse`,`dateDebut`,`heureDebut`) USING BTREE,
  UNIQUE KEY `ID` (`id`),
  KEY `COURS_MATIERES_FK` (`codeMatiere`) USING BTREE,
  KEY `COURS_SALLES_FK` (`codeSalle`),
  KEY `COURS_ENSEIGNANTS_FK` (`idEnseignants`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `cours`
--

INSERT INTO `cours` (`id`, `idEnseignants`, `codeSalle`, `groupeClasse`, `codeMatiere`, `dateDebut`, `dateFin`, `heureDebut`) VALUES
(62, 3, 2, 1, 1, '2022-01-10', '2022-01-10', '1:00PM'),
(61, 3, 1, 1, 1, '2022-01-11', '2022-01-11', '10:00AM'),
(57, 3, 3, 1, 1, '2022-01-11', '2022-01-11', '12:00PM'),
(60, 3, 2, 1, 2, '2022-01-12', '2022-01-11', '9:00AM'),
(66, 49, 1, 1, 1, '2022-01-13', '2022-01-15', '7:00AM'),
(73, 3, 2, 1, 1, '2022-01-17', '2022-01-17', '11:00AM'),
(67, 49, 2, 1, 1, '2022-01-21', '2022-01-22', '7:00AM'),
(72, 3, 2, 1, 1, '2022-01-21', '2022-01-21', '8:00AM'),
(69, 3, 1, 2, 1, '2022-01-10', '2022-01-10', '10:00AM'),
(68, 49, 3, 2, 1, '2022-01-21', '2022-01-22', '10:00AM'),
(65, 49, 2, 2, 1, '2022-10-03', '2022-10-01', '7:00AM');

-- --------------------------------------------------------

--
-- Structure de la table `enseignants`
--

DROP TABLE IF EXISTS `enseignants`;
CREATE TABLE IF NOT EXISTS `enseignants` (
  `id` int(4) NOT NULL,
  `codeMatiere` int(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ENSEIGNANT_MATIERE_FK` (`codeMatiere`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `enseignants`
--

INSERT INTO `enseignants` (`id`, `codeMatiere`) VALUES
(3, 1),
(49, 2);

-- --------------------------------------------------------

--
-- Structure de la table `etudiants`
--

DROP TABLE IF EXISTS `etudiants`;
CREATE TABLE IF NOT EXISTS `etudiants` (
  `id` int(4) NOT NULL,
  `idGroupeClasse` int(4) NOT NULL,
  `present` tinyint(4) NOT NULL,
  `excuse` varchar(14) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ETUDIANTS_GROUPES_FK` (`idGroupeClasse`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `etudiants`
--

INSERT INTO `etudiants` (`id`, `idGroupeClasse`, `present`, `excuse`) VALUES
(48, 1, 0, ''),
(50, 2, 0, '');

-- --------------------------------------------------------

--
-- Structure de la table `groupes`
--

DROP TABLE IF EXISTS `groupes`;
CREATE TABLE IF NOT EXISTS `groupes` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `libelleClasse` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `groupes`
--

INSERT INTO `groupes` (`id`, `libelleClasse`) VALUES
(1, 'M1 Info'),
(2, 'M2 Info');

-- --------------------------------------------------------

--
-- Structure de la table `matieres`
--

DROP TABLE IF EXISTS `matieres`;
CREATE TABLE IF NOT EXISTS `matieres` (
  `libelleMatiere` varchar(10) NOT NULL,
  `id` int(4) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `matieres`
--

INSERT INTO `matieres` (`libelleMatiere`, `id`) VALUES
('Maths', 1),
('Pct', 2);

-- --------------------------------------------------------

--
-- Structure de la table `presence`
--

DROP TABLE IF EXISTS `presence`;
CREATE TABLE IF NOT EXISTS `presence` (
  `id` int(4) NOT NULL,
  `idCours` int(4) NOT NULL,
  `present` tinyint(4) NOT NULL,
  `excuse` varchar(15) NOT NULL,
  PRIMARY KEY (`id`,`idCours`) USING BTREE,
  KEY `FK_COURS_PRESENCE` (`idCours`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `presence`
--

INSERT INTO `presence` (`id`, `idCours`, `present`, `excuse`) VALUES
(48, 62, 0, ''),
(48, 66, 1, ''),
(48, 67, 1, '');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `materiel` varchar(25) NOT NULL,
  `dateDebut` date NOT NULL,
  `dateFin` date NOT NULL,
  `idEnseignant` int(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_RESERVATION_ENSEIGNANT` (`idEnseignant`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id`, `materiel`, `dateDebut`, `dateFin`, `idEnseignant`) VALUES
(11, 'Projecteur', '2022-01-12', '2022-01-14', 49),
(12, 'Camera', '2022-01-04', '2022-01-14', 49),
(13, 'Camera', '2022-01-22', '2022-01-23', 3),
(14, 'Moniteur', '2022-01-05', '2022-01-15', 3),
(15, 'Projecteur', '2022-02-09', '2022-02-25', 3),
(18, 'Moniteur', '2022-03-01', '2022-03-02', 49),
(19, 'Livre', '2022-01-13', '2022-01-15', 49);

-- --------------------------------------------------------

--
-- Structure de la table `responsables`
--

DROP TABLE IF EXISTS `responsables`;
CREATE TABLE IF NOT EXISTS `responsables` (
  `id` int(4) NOT NULL,
  `idGroupeClasse` int(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `RESPONSABLES_GROUPES_FK` (`idGroupeClasse`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `responsables`
--

INSERT INTO `responsables` (`id`, `idGroupeClasse`) VALUES
(3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `salles`
--

DROP TABLE IF EXISTS `salles`;
CREATE TABLE IF NOT EXISTS `salles` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `libelleSalle` varchar(10) NOT NULL,
  `batimentSalle` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `salles`
--

INSERT INTO `salles` (`id`, `libelleSalle`, `batimentSalle`) VALUES
(1, 'U001', 'U'),
(2, 'U002', 'U'),
(3, 'U003', 'U');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

DROP TABLE IF EXISTS `utilisateurs`;
CREATE TABLE IF NOT EXISTS `utilisateurs` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `nom` varchar(25) NOT NULL,
  `prenoms` varchar(25) NOT NULL,
  `email` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `role` varchar(14) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`id`, `nom`, `prenoms`, `email`, `password`, `role`) VALUES
(3, 'DJESSOU', 'MAHUGNON', 'regisdjessou2@gmail.com', 'abcd', 'RESPONSABLE'),
(48, 'TOUKAMOU', 'KEVINOUCHOU', 'kev@gmail.com', 'abcd', 'ETUDIANT'),
(49, 'HEIDARI', 'Shahin', 'a@gmail.com', 'abcd', 'ENSEIGNANT'),
(50, 'HEIDARI', 'Shahin', 'aa@gmail.com', 'abcd', 'ETUDIANT');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `cours`
--
ALTER TABLE `cours`
  ADD CONSTRAINT `COURS_ENSEIGNANTS_FK` FOREIGN KEY (`idEnseignants`) REFERENCES `enseignants` (`id`),
  ADD CONSTRAINT `COURS_GROUPES_FK` FOREIGN KEY (`groupeClasse`) REFERENCES `groupes` (`id`),
  ADD CONSTRAINT `COURS_MATIERES_FK` FOREIGN KEY (`codeMatiere`) REFERENCES `matieres` (`id`),
  ADD CONSTRAINT `COURS_SALLES_FK` FOREIGN KEY (`codeSalle`) REFERENCES `salles` (`id`);

--
-- Contraintes pour la table `enseignants`
--
ALTER TABLE `enseignants`
  ADD CONSTRAINT `ENSEIGNANTS_UTILISATEURS_FK` FOREIGN KEY (`id`) REFERENCES `utilisateurs` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `ENSEIGNANT_MATIERE_FK` FOREIGN KEY (`codeMatiere`) REFERENCES `matieres` (`id`);

--
-- Contraintes pour la table `etudiants`
--
ALTER TABLE `etudiants`
  ADD CONSTRAINT `ETUDIANTS_GROUPES_FK` FOREIGN KEY (`idGroupeClasse`) REFERENCES `groupes` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `ETUDIANTS_UTILISATEURS_FK` FOREIGN KEY (`id`) REFERENCES `utilisateurs` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `presence`
--
ALTER TABLE `presence`
  ADD CONSTRAINT `FK_COURS_PRESENCE` FOREIGN KEY (`idCours`) REFERENCES `cours` (`id`),
  ADD CONSTRAINT `FK_ETUDIANT_PRESENCE` FOREIGN KEY (`id`) REFERENCES `etudiants` (`id`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FK_RESERVATION_ENSEIGNANT` FOREIGN KEY (`idEnseignant`) REFERENCES `enseignants` (`id`);

--
-- Contraintes pour la table `responsables`
--
ALTER TABLE `responsables`
  ADD CONSTRAINT `RESPONSABLES_GROUPES_FK` FOREIGN KEY (`idGroupeClasse`) REFERENCES `groupes` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `RESPONSABLES_UTILISATEURS_FK` FOREIGN KEY (`id`) REFERENCES `utilisateurs` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
