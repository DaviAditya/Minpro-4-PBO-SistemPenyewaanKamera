-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 12, 2026 at 03:56 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `penyewaankamera`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id_cust` varchar(20) NOT NULL,
  `nama_cust` varchar(100) NOT NULL,
  `no_telp` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id_cust`, `nama_cust`, `no_telp`) VALUES
('C01', 'Davi Aditya', '082251507348'),
('C02', 'Tama', '082251507458');

-- --------------------------------------------------------

--
-- Table structure for table `kamera`
--

CREATE TABLE `kamera` (
  `id` varchar(20) NOT NULL,
  `nama_kamera` varchar(60) NOT NULL,
  `kategori` varchar(30) NOT NULL,
  `atribut_tambahan` varchar(30) DEFAULT NULL,
  `stok` int NOT NULL,
  `harga_sewa_per3jam` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `kamera`
--

INSERT INTO `kamera` (`id`, `nama_kamera`, `kategori`, `atribut_tambahan`, `stok`, `harga_sewa_per3jam`) VALUES
('101', 'Canon EOS 80D', 'DSLR', '18-135mm IS USM', 5, '75000'),
('102', 'Canon EOS 600D', 'DSLR', '18-35mm IS II', 5, '50000'),
('103', 'Sony A6400', 'Mirrorless', 'true', 5, '90000'),
('104', 'Canon IXY 650', 'DigiCam', 'Vintage Film Warm', 5, '40000'),
('105', 'Sony Cyber-shot WX350', 'DigiCam', 'Retro Soft', 5, '45000');

-- --------------------------------------------------------

--
-- Table structure for table `penyewaan`
--

CREATE TABLE `penyewaan` (
  `kode_sewa` varchar(20) NOT NULL,
  `id_cust` varchar(20) NOT NULL,
  `id_kamera` varchar(20) NOT NULL,
  `tipe_sewa` varchar(20) NOT NULL,
  `durasi` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `penyewaan`
--

INSERT INTO `penyewaan` (`kode_sewa`, `id_cust`, `id_kamera`, `tipe_sewa`, `durasi`) VALUES
('S001', 'C01', '101', 'Harian', 2),
('S002', 'C02', '104', 'per3jam', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id_cust`);

--
-- Indexes for table `kamera`
--
ALTER TABLE `kamera`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `penyewaan`
--
ALTER TABLE `penyewaan`
  ADD PRIMARY KEY (`kode_sewa`),
  ADD KEY `id_cust` (`id_cust`),
  ADD KEY `id_kamera` (`id_kamera`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `penyewaan`
--
ALTER TABLE `penyewaan`
  ADD CONSTRAINT `penyewaan_ibfk_1` FOREIGN KEY (`id_cust`) REFERENCES `customer` (`id_cust`),
  ADD CONSTRAINT `penyewaan_ibfk_2` FOREIGN KEY (`id_kamera`) REFERENCES `kamera` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
