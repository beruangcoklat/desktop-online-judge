-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 29, 2018 at 11:23 AM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `prk`
--

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE `posts` (
  `id` int(11) NOT NULL,
  `description` text NOT NULL,
  `user_id` int(11) NOT NULL,
  `problem_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `posts`
--

INSERT INTO `posts` (`id`, `description`, `user_id`, `problem_id`) VALUES
(1, 'imba alex', 1, 1),
(2, 'haha hihi', 2, 1),
(3, 'wew', 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `problems`
--

CREATE TABLE `problems` (
  `id` int(11) NOT NULL,
  `title` text NOT NULL,
  `description` text NOT NULL,
  `answer` text NOT NULL,
  `input` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `problems`
--

INSERT INTO `problems` (`id`, `title`, `description`, `answer`, `input`) VALUES
(1, 'Penjumlahan', 'Diberikan 2 buah angka a dan b. Hitung jumlah dari angka a dan b', '5\n', '2 3\n'),
(2, 'Max', 'Diberikan n buah angka. Tentukan angka terbesar dari angka-angka tersebut.', '100\n', '5 1 2 3 4 100\n');

-- --------------------------------------------------------

--
-- Table structure for table `statuses`
--

CREATE TABLE `statuses` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `statuses`
--

INSERT INTO `statuses` (`id`, `name`) VALUES
(1, 'Accepted'),
(2, 'Wrong Answer'),
(3, 'Compile Error'),
(4, 'Time Limit Exceeded'),
(5, 'Runtime Error');

-- --------------------------------------------------------

--
-- Table structure for table `submissions`
--

CREATE TABLE `submissions` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `problem_id` int(11) NOT NULL,
  `status_id` int(11) NOT NULL,
  `submission` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `submissions`
--

INSERT INTO `submissions` (`id`, `user_id`, `problem_id`, `status_id`, `submission`) VALUES
(1, 1, 1, 5, 'import java.util.Scanner;\n\nclass Main{\n	public static void main(String[] args){\n		Scanner s = new Scanner(System.in);\n		int a = s.nextInt();\n		int b = s.nextInt();\n		int r = (a+b)/0;\n		System.out.println(r);\n	}\n}'),
(2, 1, 1, 2, 'import java.util.Scanner;\n\nclass Main{\n	public static void main(String[] args){\n		Scanner s = new Scanner(System.in);\n		int a = s.nextInt();\n		int b = s.nextInt();\n		int r = (a+b)+1;\n		System.out.println(r);\n	}\n}'),
(3, 1, 1, 3, 'import java.util.Scanner;\n\nclass Main{\n	publikkkkkkkkkkk static void main(String[] args){\n		Scanner s = new Scanner(System.in);\n		int a = s.nextInt();\n		int b = s.nextInt();\n		int r = (a+b)+1;\n		System.out.println(r);\n	}\n}'),
(4, 1, 1, 4, 'import java.util.Scanner;\n\nclass Main{\n	public static void main(String[] args){\n		Scanner s = new Scanner(System.in);\n		int a = s.nextInt();\n		int b = s.nextInt();\n		int r = (a+b);\n		for(int i=0 ; i<999999 ; i++) System.out.println(r);\n	}\n}'),
(5, 1, 1, 1, 'import java.util.Scanner;\n\nclass Main{\n	public static void main(String[] args){\n		Scanner s = new Scanner(System.in);\n		int a = s.nextInt();\n		int b = s.nextInt();\n		int r = (a+b);\n		System.out.println(r);\n	}\n}');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'tes', 'tes'),
(2, 'alex', 'alex');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `problems`
--
ALTER TABLE `problems`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `statuses`
--
ALTER TABLE `statuses`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `submissions`
--
ALTER TABLE `submissions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `problems`
--
ALTER TABLE `problems`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `statuses`
--
ALTER TABLE `statuses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `submissions`
--
ALTER TABLE `submissions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
