-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 16-Jun-2021 às 22:52
-- Versão do servidor: 10.1.13-MariaDB
-- PHP Version: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gowl`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_emprestimos`
--

CREATE TABLE `tb_emprestimos` (
  `id` int(11) NOT NULL,
  `data_emprestimo` datetime DEFAULT NULL,
  `data_devolucao` datetime DEFAULT NULL,
  `observacoes` text,
  `tb_funcionarios_id` int(11) DEFAULT NULL,
  `tb_livros_id` int(11) DEFAULT NULL,
  `tb_livros_tb_fornecedores_id` int(11) DEFAULT NULL,
  `tb_leitores_id` int(11) DEFAULT NULL,
  `data_entrega_agendada` datetime DEFAULT NULL,
  `tb_funcionarios_iddevol` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tb_emprestimos`
--

INSERT INTO `tb_emprestimos` (`id`, `data_emprestimo`, `data_devolucao`, `observacoes`, `tb_funcionarios_id`, `tb_livros_id`, `tb_livros_tb_fornecedores_id`, `tb_leitores_id`, `data_entrega_agendada`, `tb_funcionarios_iddevol`) VALUES
(1, '2021-06-13 22:56:51', '2021-06-13 22:57:04', 'ok', 1, 2, NULL, 1, '2021-06-16 22:56:51', 1),
(2, '2021-06-13 23:38:09', '2021-06-13 23:38:23', 'ok', 1, 2, NULL, 1, '2021-06-16 23:38:09', 1),
(3, '2021-06-13 23:38:16', '2021-06-13 23:38:20', 'ok', 1, 1, NULL, 1, '2021-06-14 23:38:16', 1),
(4, '2021-06-14 00:19:44', '2021-06-14 00:45:42', 'ok', 1, 2, NULL, 1, '2021-06-17 00:19:44', 1),
(5, '2021-06-14 00:19:55', '2021-06-14 00:45:52', 'ok', 1, 1, NULL, 1, '2021-06-15 00:19:55', 1),
(6, '2021-06-14 00:46:09', '2021-06-14 00:58:42', 'ok', 1, 2, NULL, 1, '2021-06-17 00:46:09', 1),
(7, '2021-06-14 00:47:46', '2021-06-14 00:58:48', 'ok', 1, 1, NULL, 1, '2021-06-15 00:47:46', 1),
(8, '2021-06-14 00:59:00', '2021-06-14 00:59:10', 'ok', 1, 2, NULL, 1, '2021-06-17 00:59:00', 1),
(9, '2021-06-14 00:59:02', '2021-06-14 00:59:12', 'ok', 1, 1, NULL, 1, '2021-06-15 00:59:02', 1),
(10, '2021-06-14 00:59:20', '2021-06-14 01:02:50', 'ok', 1, 1, NULL, 1, '2021-06-15 00:59:20', 1),
(11, '2021-06-14 01:02:35', '2021-06-14 01:02:52', 'ok', 1, 2, NULL, 1, '2021-06-17 01:02:35', 1),
(12, '2021-06-14 01:02:58', '2021-06-14 01:03:03', 'ok', 1, 2, NULL, 1, '2021-06-17 01:02:58', 1),
(13, '2021-06-14 01:03:10', '2021-06-14 01:03:17', 'ok', 1, 1, NULL, 1, '2021-06-15 01:03:10', 1),
(14, '2021-06-14 01:03:12', '2021-06-14 01:03:23', 'ok', 1, 2, NULL, 1, '2021-06-17 01:03:12', 1),
(15, '2021-06-14 01:03:33', '2021-06-14 01:20:34', 'ok', 1, 2, NULL, 1, '2021-06-17 01:03:33', 1),
(16, '2021-06-14 01:03:34', '2021-06-14 01:20:32', 'ok', 1, 1, NULL, 1, '2021-06-15 01:03:34', 1),
(17, '2021-06-14 01:20:42', '2021-06-14 01:21:21', 'ok', 1, 2, NULL, 1, '2021-06-17 01:20:42', 1),
(18, '2021-06-14 01:21:06', '2021-06-14 01:21:23', 'ok', 1, 1, NULL, 1, '2021-06-15 01:21:06', 1),
(19, '2021-06-14 01:28:47', '2021-06-14 02:41:01', 'ok', 1, 2, NULL, 1, '2021-06-17 01:28:47', 1),
(20, '2021-06-14 02:40:58', '2021-06-14 02:41:04', 'ok', 1, 1, NULL, 1, '2021-06-15 02:40:58', 1),
(21, '2021-06-14 02:53:29', '2021-06-14 02:54:20', 'ok', 1, 2, NULL, 1, '2021-06-17 02:53:29', 1),
(22, '2021-06-14 02:53:55', '2021-06-14 02:54:15', 'ok', 1, 1, NULL, 1, '2021-06-15 02:53:55', 1),
(23, '2021-06-04 04:02:06', '2021-06-14 14:42:22', 'ok', 1, 1, NULL, 1, '2021-06-05 04:02:06', 1),
(24, '2021-06-14 13:56:24', '2021-06-14 14:37:27', 'ok', 1, 2, NULL, 1, '2021-06-17 13:56:24', 1),
(25, '2021-06-14 14:42:44', '2021-06-14 23:00:17', 'ok', 1, 1, NULL, 1, '2021-06-15 14:42:44', 1),
(26, '2021-06-14 19:23:36', '2021-06-14 19:23:49', 'ok', 1, 2, NULL, 1, '2021-06-17 19:23:36', 1),
(27, '2021-06-14 23:00:17', '2021-06-14 23:46:19', 'ok', 1, 1, NULL, 1, '2021-06-15 23:00:17', 1),
(28, '2021-06-14 23:02:11', '2021-06-14 23:02:22', 'ok', 1, 1, NULL, 1, '2021-06-15 23:02:11', 1),
(29, '2021-06-14 23:46:09', '2021-06-14 23:46:15', 'ok', 1, 1, NULL, 1, '2021-06-15 23:46:09', 1),
(30, '2021-06-14 23:46:30', '2021-06-14 23:46:56', 'ok', 1, 1, NULL, 1, '2021-06-15 23:46:30', 1),
(31, '2021-06-14 23:47:04', '2021-06-14 23:47:18', 'ok', 1, 1, NULL, 1, '2021-06-15 23:47:04', 1),
(32, '2021-06-14 23:47:26', '2021-06-14 23:47:36', 'ok', 1, 1, NULL, 1, '2021-06-15 23:47:26', 1),
(33, '2021-06-14 23:47:50', '2021-06-14 23:47:55', 'ok', 1, 1, NULL, 1, '2021-06-15 23:47:50', 1),
(34, '2021-06-14 23:47:55', '2021-06-14 23:48:59', 'ok', 1, 1, NULL, 1, '2021-06-15 23:47:55', 1),
(35, '2021-06-14 23:48:27', '2021-06-14 23:48:34', 'ok', 1, 2, NULL, 1, '2021-06-17 23:48:27', 1),
(36, '2021-06-14 23:48:34', '2021-06-14 23:49:02', 'ok', 1, 2, NULL, 1, '2021-06-17 23:48:34', 1),
(37, '2021-06-14 23:49:25', '2021-06-14 23:49:43', 'ok', 1, 1, NULL, 1, '2021-06-15 23:49:25', 1),
(38, '2021-06-14 23:49:43', '2021-06-15 00:33:07', 'ok reeemprestado', 1, 1, NULL, 1, '2021-06-15 23:49:43', 1),
(39, '2021-06-15 00:44:25', '2021-06-15 00:44:39', 'ok', 1, 1, NULL, 1, '2021-06-16 00:44:25', 1),
(40, '2021-06-16 16:42:23', NULL, 'ok teste add to obs', 1, 1, NULL, 1, '2021-06-16 16:42:23', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_fornecedores`
--

CREATE TABLE `tb_fornecedores` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `cnpj` varchar(100) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `telefone` varchar(30) DEFAULT NULL,
  `celular` varchar(30) DEFAULT NULL,
  `cep` varchar(100) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `numero` varchar(50) DEFAULT NULL,
  `complemento` varchar(200) DEFAULT NULL,
  `bairro` varchar(100) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tb_fornecedores`
--

INSERT INTO `tb_fornecedores` (`id`, `nome`, `cnpj`, `email`, `telefone`, `celular`, `cep`, `endereco`, `numero`, `complemento`, `bairro`, `cidade`, `estado`) VALUES
(1, 'Fornecedor Teste', '231.231.231-23', 'teste@test', '(42)3232-1231', '(23)42342-3423', '21342-342', 'dfsdfsdfsdfsdf', '', 'asdasd', 'asdasdasd', 'asdasdasd', 'RR'),
(2, 'sdfsdfsdf', '234.234.234-23', 'sdfsdfsdfsdf', '(23)4234-2342', '(23)42342-3423', '21342-342', 'sdfsdf', '123', 'sdfsdf', 'sdfsdf', 'sdfsdfs', 'AP');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_funcionarios`
--

CREATE TABLE `tb_funcionarios` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `rg` varchar(30) DEFAULT NULL,
  `cpf` varchar(20) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `senha` varchar(10) NOT NULL,
  `cargo` varchar(100) NOT NULL,
  `nivel_acesso` varchar(50) NOT NULL,
  `telefone` varchar(30) DEFAULT NULL,
  `celular` varchar(30) DEFAULT NULL,
  `cep` varchar(100) NOT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `numero` varchar(50) DEFAULT NULL,
  `complemento` varchar(200) DEFAULT NULL,
  `bairro` varchar(100) DEFAULT NULL,
  `cidade` varchar(100) NOT NULL,
  `estado` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tb_funcionarios`
--

INSERT INTO `tb_funcionarios` (`id`, `nome`, `rg`, `cpf`, `email`, `senha`, `cargo`, `nivel_acesso`, `telefone`, `celular`, `cep`, `endereco`, `numero`, `complemento`, `bairro`, `cidade`, `estado`) VALUES
(1, 'Administrador', '7.275.275-1', '033.330.003-13', 'admin@admin', 'admin', 'Administrador', 'Usuario', '(  )    -    ', '(12)31231-2312', '84570-000', 'rua Miguel Grenteski', '13', 'Casa', 'Eldorado', 'Mallet', 'PR');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_leitores`
--

CREATE TABLE `tb_leitores` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `rg` varchar(30) DEFAULT NULL,
  `cpf` varchar(20) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `telefone` varchar(30) DEFAULT NULL,
  `celular` varchar(30) DEFAULT NULL,
  `cep` varchar(100) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `numero` varchar(50) DEFAULT NULL,
  `complemento` varchar(200) DEFAULT NULL,
  `bairro` varchar(100) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  `tipo` varchar(15) DEFAULT NULL,
  `is_locked` tinyint(4) DEFAULT NULL,
  `curso` varchar(45) DEFAULT NULL,
  `curso_ano` varchar(45) DEFAULT NULL,
  `qtd_emprestimos` int(3) DEFAULT NULL,
  `emprestmax` int(11) DEFAULT NULL,
  `observacoes` varchar(225) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tb_leitores`
--

INSERT INTO `tb_leitores` (`id`, `nome`, `rg`, `cpf`, `email`, `telefone`, `celular`, `cep`, `endereco`, `numero`, `complemento`, `bairro`, `cidade`, `estado`, `tipo`, `is_locked`, `curso`, `curso_ano`, `qtd_emprestimos`, `emprestmax`, `observacoes`) VALUES
(1, 'Leitor 1', '111.111.111-1', '111.111.111-11', 'leitor@test.com', '(42)99999-9999', '(42)99999-9999', '84570-000', 'Rua Tal', '100', 'casa', 'Centro', 'Mallet', 'PR', 'Estudante', 0, 'Letras Ingles / Noturno', '4', 0, 3, 'leitor ok - primeiro cadastro - cadastro completo'),
(2, 'Leitor numero 2', ' .   .   - ', '123.123.123-23', 'leitor2@leitor2.com', '(  )    -    ', '(42)45355-6666', '84570-000', 'Rua 2', '1212', 'Casa', 'Centro', 'Mallet', 'PR', 'Professor', 0, 'Letras', '0', 0, 0, 'Professor - emprestimos ilimitados');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_livros`
--

CREATE TABLE `tb_livros` (
  `id` int(11) NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `autor` varchar(45) NOT NULL,
  `editora` varchar(45) NOT NULL,
  `isbn` varchar(13) NOT NULL,
  `ano` varchar(4) NOT NULL,
  `serie` varchar(45) DEFAULT NULL,
  `edicao` varchar(45) DEFAULT NULL,
  `idioma` varchar(25) NOT NULL,
  `tb_fornecedores_id` int(11) DEFAULT NULL,
  `piso` varchar(45) DEFAULT NULL,
  `corredor` varchar(45) DEFAULT NULL,
  `posicao` varchar(45) DEFAULT NULL,
  `secao` varchar(45) DEFAULT NULL,
  `disponibilidade` varchar(15) NOT NULL,
  `observacoes` varchar(225) DEFAULT NULL,
  `is_emprestado` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tb_livros`
--

INSERT INTO `tb_livros` (`id`, `titulo`, `autor`, `editora`, `isbn`, `ano`, `serie`, `edicao`, `idioma`, `tb_fornecedores_id`, `piso`, `corredor`, `posicao`, `secao`, `disponibilidade`, `observacoes`, `is_emprestado`) VALUES
(1, 'Alice no Pais das Maravilhas', 'Lewis Carrol', 'Dumi Edit', '1234567890123', '1894', 'Especial', '25', 'Portugues', 1, 'Piso 2', 'corredor 1', '1A', 'Literatura Universal', '0', 'ok teste add to obs', 1),
(2, 'A Revolução dos Bichos', 'George Orwell', 'Dumi Edit', '0987654321234', '1934', 'Must be Read Books', '2', 'Portugues', 1, 'Piso 2', 'corredor 1', '1A', 'Literatura Universal', '3', 'ok', 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_multa`
--

CREATE TABLE `tb_multa` (
  `idmulta` int(11) NOT NULL,
  `dias_atraso` int(6) NOT NULL,
  `valor_multa` decimal(10,2) DEFAULT NULL,
  `esta_pago` tinyint(4) DEFAULT NULL,
  `tb_leitores_id` int(11) NOT NULL,
  `tb_emprestimos_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tb_multa`
--

INSERT INTO `tb_multa` (`idmulta`, `dias_atraso`, `valor_multa`, `esta_pago`, `tb_leitores_id`, `tb_emprestimos_id`) VALUES
(1, -2, '-6.00', 0, 1, 6),
(2, 0, '0.00', 0, 1, 25);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_opcoes`
--

CREATE TABLE `tb_opcoes` (
  `id` int(11) NOT NULL,
  `data` varchar(45) NOT NULL,
  `parentid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tb_opcoes`
--

INSERT INTO `tb_opcoes` (`id`, `data`, `parentid`) VALUES
(1, 'piso', NULL),
(2, 'corredor', NULL),
(3, 'secao', NULL),
(4, 'posicao', NULL),
(5, 'disponibilidade', NULL),
(6, 'printerurl', NULL),
(7, 'instituicaonome', NULL),
(8, 'instiuicaoendereco', NULL),
(9, 'tiposdeusuarios', NULL),
(10, 'server url', NULL),
(11, 'libraryname', NULL),
(12, 'libraryAddress', NULL),
(13, '0', 5),
(14, '3', 5),
(15, 'Biblioteca de Hogwarts', 7),
(16, 'Rua Epsilon, 888, Cidade Imaginaria, LA ', 8),
(17, 'piso 1', 1),
(18, 'Estudante', 9),
(19, 'Professor', 9),
(20, 'corredor 1', 2),
(21, '1A', 3),
(22, 'Literatura Universal', 4),
(23, '127.0.0.1', 12),
(24, 'Nome da Biblioteca', NULL),
(25, 'valor_multa', NULL),
(26, '300', 25),
(27, 'Piso 2', 1),
(28, 'piso 3', 1),
(29, 'Piso 5', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_emprestimos`
--
ALTER TABLE `tb_emprestimos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_tb_emprestimos_tb_funcionarios1_idx` (`tb_funcionarios_id`),
  ADD KEY `fk_tb_emprestimos_tb_livros1_idx` (`tb_livros_id`,`tb_livros_tb_fornecedores_id`),
  ADD KEY `fk_tb_emprestimos_tb_leitores1_idx` (`tb_leitores_id`),
  ADD KEY `fk_tb_emprestimos_tb_funcionarios2_idx` (`tb_funcionarios_iddevol`);

--
-- Indexes for table `tb_fornecedores`
--
ALTER TABLE `tb_fornecedores`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_funcionarios`
--
ALTER TABLE `tb_funcionarios`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_leitores`
--
ALTER TABLE `tb_leitores`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_livros`
--
ALTER TABLE `tb_livros`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_tb_livros_tb_fornecedores1_idx` (`tb_fornecedores_id`);

--
-- Indexes for table `tb_multa`
--
ALTER TABLE `tb_multa`
  ADD PRIMARY KEY (`idmulta`,`tb_emprestimos_id`),
  ADD KEY `fk_multa_tb_leitores1_idx` (`tb_leitores_id`),
  ADD KEY `fk_tb_multa_tb_emprestimos1_idx` (`tb_emprestimos_id`);

--
-- Indexes for table `tb_opcoes`
--
ALTER TABLE `tb_opcoes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Opcoes_Opcoes1_idx` (`parentid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_emprestimos`
--
ALTER TABLE `tb_emprestimos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;
--
-- AUTO_INCREMENT for table `tb_fornecedores`
--
ALTER TABLE `tb_fornecedores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `tb_funcionarios`
--
ALTER TABLE `tb_funcionarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `tb_leitores`
--
ALTER TABLE `tb_leitores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `tb_livros`
--
ALTER TABLE `tb_livros`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `tb_multa`
--
ALTER TABLE `tb_multa`
  MODIFY `idmulta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `tb_opcoes`
--
ALTER TABLE `tb_opcoes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `tb_emprestimos`
--
ALTER TABLE `tb_emprestimos`
  ADD CONSTRAINT `fk_tb_emprestimos_tb_funcionarios1` FOREIGN KEY (`tb_funcionarios_id`) REFERENCES `tb_funcionarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tb_emprestimos_tb_funcionarios2` FOREIGN KEY (`tb_funcionarios_iddevol`) REFERENCES `tb_funcionarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tb_emprestimos_tb_leitores1` FOREIGN KEY (`tb_leitores_id`) REFERENCES `tb_leitores` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tb_emprestimos_tb_livros1` FOREIGN KEY (`tb_livros_id`) REFERENCES `tb_livros` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `tb_livros`
--
ALTER TABLE `tb_livros`
  ADD CONSTRAINT `fk_tb_livros_tb_fornecedores1` FOREIGN KEY (`tb_fornecedores_id`) REFERENCES `tb_fornecedores` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `tb_multa`
--
ALTER TABLE `tb_multa`
  ADD CONSTRAINT `fk_multa_tb_leitores1` FOREIGN KEY (`tb_leitores_id`) REFERENCES `tb_leitores` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tb_multa_tb_emprestimos1` FOREIGN KEY (`tb_emprestimos_id`) REFERENCES `tb_emprestimos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `tb_opcoes`
--
ALTER TABLE `tb_opcoes`
  ADD CONSTRAINT `fk_Opcoes_Opcoes1` FOREIGN KEY (`parentid`) REFERENCES `tb_opcoes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
