-- phpMyAdmin SQL Dump
-- version 3.1.3.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 22, 2016 at 10:45 AM
-- Server version: 5.1.33
-- PHP Version: 5.2.9

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `serenada`
--

-- --------------------------------------------------------

--
-- Table structure for table `t_rules`
--

CREATE TABLE IF NOT EXISTS `t_rules` (
  `tugas` double NOT NULL,
  `kuis` double NOT NULL,
  `uts` double NOT NULL,
  `uas` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_rules`
--

INSERT INTO `t_rules` (`tugas`, `kuis`, `uts`, `uas`) VALUES
(0.1, 0.1, 0.35, 0.45);

-- --------------------------------------------------------

--
-- Table structure for table `t_user`
--

CREATE TABLE IF NOT EXISTS `t_user` (
  `id` varchar(20) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `email` varchar(99) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `hp` varchar(255) NOT NULL,
  `jk` varchar(255) NOT NULL,
  `tugas` double NOT NULL,
  `kuis` double NOT NULL,
  `uts` double NOT NULL,
  `uas` double NOT NULL,
  `gpa` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_user`
--

INSERT INTO `t_user` (`id`, `nama`, `email`, `alamat`, `hp`, `jk`, `tugas`, `kuis`, `uts`, `uas`, `gpa`) VALUES
('11111', 'Fadhil', '', 'Jakarta', '2015-12-08', 'Laki - Laki', 90, 90, 90, 30, 0),
('11112', 'Ojan', '', 'Bandung', '0000-00-00', 'Laki - Laki', 10, 10, 30, 90, 0),
('11113', 'Rengky', '', 'Jakarta', '2015-12-08', 'Laki - Laki', 90, 90, 90, 90, 0),
('11114', 'David', '', 'Bandung', '0000-00-00', 'Laki - Laki', 0, 0, 0, 100, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `nama`, `email`, `password`) VALUES
(3, 'adi hidayat ', 'adi@yahoo.com', 'adi');
