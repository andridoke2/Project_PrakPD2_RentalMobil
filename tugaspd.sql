-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 03, 2018 at 02:47 AM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tugaspd`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `ID_CUSTOMER` varchar(12) NOT NULL,
  `NAMA_DPN` varchar(30) NOT NULL,
  `NAMA_BLKG` varchar(30) NOT NULL,
  `JK` char(1) NOT NULL,
  `ALAMAT` varchar(50) NOT NULL,
  `TELP_CUSTOMER` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`ID_CUSTOMER`, `NAMA_DPN`, `NAMA_BLKG`, `JK`, `ALAMAT`, `TELP_CUSTOMER`) VALUES
('1', 'Andri', 'Doke', 'L', 'Malang', '082152085281');

-- --------------------------------------------------------

--
-- Table structure for table `detail_penyewaan`
--

CREATE TABLE `detail_penyewaan` (
  `ID_UNIT` int(12) NOT NULL,
  `NO_SEWA` varchar(12) NOT NULL,
  `HARGA_SEWA` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `merk_unit`
--

CREATE TABLE `merk_unit` (
  `ID_JENIS` varchar(12) NOT NULL,
  `NAMA_JENIS` varchar(30) NOT NULL,
  `KET_JENIS` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `merk_unit`
--

INSERT INTO `merk_unit` (`ID_JENIS`, `NAMA_JENIS`, `KET_JENIS`) VALUES
('1', 'Lamboerghini', 'Black');

-- --------------------------------------------------------

--
-- Table structure for table `penyewaan`
--

CREATE TABLE `penyewaan` (
  `NO_SEWA` varchar(12) NOT NULL,
  `ID_CUSTOMER` varchar(12) NOT NULL,
  `JML_HARI` int(11) DEFAULT NULL,
  `JAMINAN` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `unit`
--

CREATE TABLE `unit` (
  `ID_UNIT` int(12) NOT NULL,
  `ID_JENIS` varchar(12) NOT NULL,
  `NAMA_UNIT` varchar(30) NOT NULL,
  `TAHUN` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `unit`
--

INSERT INTO `unit` (`ID_UNIT`, `ID_JENIS`, `NAMA_UNIT`, `TAHUN`) VALUES
(1, '1', 'Galardo', '2019');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`ID_CUSTOMER`);

--
-- Indexes for table `detail_penyewaan`
--
ALTER TABLE `detail_penyewaan`
  ADD PRIMARY KEY (`ID_UNIT`),
  ADD KEY `NO_SEWA` (`NO_SEWA`);

--
-- Indexes for table `merk_unit`
--
ALTER TABLE `merk_unit`
  ADD PRIMARY KEY (`ID_JENIS`);

--
-- Indexes for table `penyewaan`
--
ALTER TABLE `penyewaan`
  ADD PRIMARY KEY (`NO_SEWA`),
  ADD KEY `ID_CUSTOMER` (`ID_CUSTOMER`);

--
-- Indexes for table `unit`
--
ALTER TABLE `unit`
  ADD PRIMARY KEY (`ID_UNIT`),
  ADD KEY `ID_JENIS` (`ID_JENIS`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_penyewaan`
--
ALTER TABLE `detail_penyewaan`
  ADD CONSTRAINT `detail_penyewaan_ibfk_1` FOREIGN KEY (`NO_SEWA`) REFERENCES `penyewaan` (`NO_SEWA`);

--
-- Constraints for table `penyewaan`
--
ALTER TABLE `penyewaan`
  ADD CONSTRAINT `penyewaan_ibfk_1` FOREIGN KEY (`ID_CUSTOMER`) REFERENCES `customer` (`ID_CUSTOMER`);

--
-- Constraints for table `unit`
--
ALTER TABLE `unit`
  ADD CONSTRAINT `unit_ibfk_1` FOREIGN KEY (`ID_JENIS`) REFERENCES `merk_unit` (`ID_JENIS`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
