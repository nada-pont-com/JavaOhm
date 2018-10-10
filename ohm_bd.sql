-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Tempo de geração: 10/10/2018 às 20:48
-- Versão do servidor: 5.7.11-log
-- Versão do PHP: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `ohm_bd`
--
CREATE DATABASE IF NOT EXISTS `ohm_bd` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `ohm_bd`;

-- --------------------------------------------------------

--
-- Estrutura para tabela `baterias`
--

CREATE TABLE `baterias` (
  `id` int(10) UNSIGNED NOT NULL,
  `nome` varchar(45) NOT NULL,
  `armazenamento` int(10) UNSIGNED NOT NULL,
  `desc` text NOT NULL,
  `fase` smallint(1) UNSIGNED NOT NULL,
  `valor` int(10) UNSIGNED NOT NULL COMMENT 'Valor da pesquisa em energia.'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Fazendo dump de dados para tabela `baterias`
--

INSERT INTO `baterias` (`id`, `nome`, `armazenamento`, `desc`, `fase`, `valor`) VALUES
(1, 'bateria', 15000, 'Uma bateria que armazena 15k de energia.', 1, 500),
(2, 'Bateria', 30000, 'uma bateria', 2, 10000);

-- --------------------------------------------------------

--
-- Estrutura para tabela `clientes`
--

CREATE TABLE `clientes` (
  `id` int(10) UNSIGNED ZEROFILL NOT NULL COMMENT 'Armazena o id do cliente, cada id é único.',
  `energia` int(10) UNSIGNED NOT NULL COMMENT 'Armazena a energia que o jogador possui.',
  `dinheiro` int(10) UNSIGNED NOT NULL COMMENT 'Armazena o dinheiro do jogador.',
  `franklin` int(10) UNSIGNED NOT NULL COMMENT 'Armazena a moeda especial, conhecida como franklin, do jogador.',
  `franklin_geral` bigint(20) UNSIGNED NOT NULL COMMENT 'Armazena o total de moedas franklin que o jogador ja possuiu.',
  `fase` tinyint(4) NOT NULL COMMENT 'Armazena a fase na qual o jogador está.',
  `tempo_jogo` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Armazena o tempo de jogo do jogador.',
  `maior_pontuacao` int(10) UNSIGNED NOT NULL COMMENT 'Armazena a maior pontuação do jogadorl',
  `linha_da_pontuacao` tinyint(3) UNSIGNED NOT NULL COMMENT 'Armazena a linha do tempo em que o jogador fez a maior pontuação.',
  `linha_atual` tinyint(3) UNSIGNED NOT NULL COMMENT 'Armazena a linha do tempo atual do jogador.',
  `usuarios_login` varchar(45) NOT NULL COMMENT 'Armazena o login de usuário utilizado pelo jogador para acessar sua conta.',
  `dinheiro_geral` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='A tabela "clientes" armazena os dados dos clientes, ou sjea, é referentes aos usuários com a permissão de jogadores, afinal ela é responsável por armazenar os dados dos jogadores com relação ao jogo, como sua quantidade de dinheirom faze que se encontram e similiares.';

--
-- Fazendo dump de dados para tabela `clientes`
--

INSERT INTO `clientes` (`id`, `energia`, `dinheiro`, `franklin`, `franklin_geral`, `fase`, `tempo_jogo`, `maior_pontuacao`, `linha_da_pontuacao`, `linha_atual`, `usuarios_login`, `dinheiro_geral`) VALUES
(0000000012, 1835, 249956954, 0, 0, 2, '2000-01-01 02:02:49', 500000019, 1, 1, '1234', 250000010);

-- --------------------------------------------------------

--
-- Estrutura para tabela `clientes_tem_baterias`
--

CREATE TABLE `clientes_tem_baterias` (
  `clientes_id` int(10) UNSIGNED ZEROFILL NOT NULL,
  `baterias_id` int(10) UNSIGNED NOT NULL,
  `quantidade` smallint(5) UNSIGNED NOT NULL COMMENT 'Armazena a quantidade determinadas de baterias que o jogador possui.'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Fazendo dump de dados para tabela `clientes_tem_baterias`
--

INSERT INTO `clientes_tem_baterias` (`clientes_id`, `baterias_id`, `quantidade`) VALUES
(0000000012, 2, 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `clientes_tem_maquinas`
--

CREATE TABLE `clientes_tem_maquinas` (
  `clientes_id` int(10) UNSIGNED ZEROFILL NOT NULL COMMENT 'Armazena o id do cliente que possui determinada máquina, associa a tabela "clientes" com a tabela "maquinas".',
  `maquinas_id` tinyint(3) UNSIGNED ZEROFILL NOT NULL COMMENT 'Armazena o id da máquina que o cliente possui, associa a tabela "clientes" com a tabela "maquinas".',
  `multiplicador` smallint(5) UNSIGNED NOT NULL COMMENT 'Armazena o multiplicador que as máquinas podem ganhar através das pesquisas que o jogador pode fazer.',
  `quantidade` smallint(5) UNSIGNED NOT NULL COMMENT 'Armazena a quantidade de determinadas máquinas que o jogador possui.'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='A tabela "clientes_tem_maquinas" é responsável por armazenar dados que relacionam os jogadores com as máquinas, assim englobando sua quantidade e o multiplicador que elas podem possuir como bonus.';

--
-- Fazendo dump de dados para tabela `clientes_tem_maquinas`
--

INSERT INTO `clientes_tem_maquinas` (`clientes_id`, `maquinas_id`, `multiplicador`, `quantidade`) VALUES
(0000000012, 001, 1, 1),
(0000000012, 003, 1, 20),
(0000000012, 004, 1, 0);

-- --------------------------------------------------------

--
-- Estrutura para tabela `clientes_tem_pesquisas`
--

CREATE TABLE `clientes_tem_pesquisas` (
  `clientes_id` int(10) UNSIGNED ZEROFILL NOT NULL,
  `pesquisas_id` int(10) UNSIGNED NOT NULL,
  `tempo` time NOT NULL,
  `estado` varchar(10) NOT NULL COMMENT 'Armazena o estado da pesuisa se ele está finalizada, iniciada ou n iniciada'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Fazendo dump de dados para tabela `clientes_tem_pesquisas`
--

INSERT INTO `clientes_tem_pesquisas` (`clientes_id`, `pesquisas_id`, `tempo`, `estado`) VALUES
(0000000012, 1, '00:00:00', 'finalizada');

-- --------------------------------------------------------

--
-- Estrutura para tabela `denuncias`
--

CREATE TABLE `denuncias` (
  `id` int(10) UNSIGNED NOT NULL,
  `denuncia` varchar(25) NOT NULL,
  `clientes_id` int(10) UNSIGNED ZEROFILL NOT NULL,
  `usuarios_login` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura para tabela `fases`
--

CREATE TABLE `fases` (
  `id` int(10) UNSIGNED ZEROFILL NOT NULL COMMENT 'Armazena o id da fase, ele é único.',
  `tempo_jogo` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Armazena o tempo em que o jogador permaneceu na fase desde seu início.',
  `franklin` int(10) UNSIGNED NOT NULL COMMENT 'Armazena a moeda especial, conhecida como franklin, do jogador.',
  `frankling_geral` int(10) UNSIGNED ZEROFILL NOT NULL COMMENT 'Armazena o total de moedas franklin que o jogador ja possuiu.',
  `dinheiro` int(10) UNSIGNED NOT NULL COMMENT 'Armazena o dinheiro do jogador.',
  `energia` int(10) UNSIGNED NOT NULL COMMENT 'Armazena a energia que o jogador possui.',
  `clientes_id` int(10) UNSIGNED ZEROFILL NOT NULL COMMENT 'Armazena o id do cliente e o associa a fase em que está.'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='A tabela "fases" armazena os dados referentes as fases por onde o jogador passa, assim salvando as informações do jogador quando ele deixa a sessão ou passa para a próxima fase. Ela serve como backup caso o jogador resolva sairda sessão e/ou reiniciar a fase.';

-- --------------------------------------------------------

--
-- Estrutura para tabela `maquinas`
--

CREATE TABLE `maquinas` (
  `id` tinyint(3) UNSIGNED ZEROFILL NOT NULL COMMENT 'Armazena o id das máquinas, cada id é único.',
  `valor` int(10) UNSIGNED NOT NULL COMMENT 'Armazena o valor/custo das máquinas.',
  `pps` int(10) UNSIGNED NOT NULL COMMENT 'Armazena a produção por segundo de energia da máquina.',
  `fase` tinyint(3) UNSIGNED ZEROFILL NOT NULL COMMENT 'Armazena a fase em que a máquina pode ser comprada/pode aparecer.',
  `subFase` tinyint(3) UNSIGNED ZEROFILL DEFAULT NULL COMMENT 'Armazena a fase em que a máquina pode ser comprada/pode aparecer.',
  `desc` text NOT NULL COMMENT 'Armazena a descrição da máquina.',
  `nome` varchar(45) NOT NULL COMMENT 'Armazena o nome da máquina'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='A tabela "maquinas" armazena os dados referentes as máquinas presentes no jogo, como o valor de sua compra, produção por segundo, a fase onde são encontradas e a descrição delas.';

--
-- Fazendo dump de dados para tabela `maquinas`
--

INSERT INTO `maquinas` (`id`, `valor`, `pps`, `fase`, `subFase`, `desc`, `nome`) VALUES
(001, 0, 1, 010, NULL, 'Manivela que ao ser girada gera energia.', 'manivela'),
(002, 10, 1, 001, NULL, 'Manivela automática que ao girar automaticamente e gera energia.', 'manivela automática'),
(003, 1000, 5, 002, 003, 'Um moinho de vento que produz energia através do vento.', 'Moinho de vento'),
(004, 2500, 10, 002, 003, 'Moinho de água que produz  energia através de correnteza da água.', 'Moinho de agua'),
(005, 8000, 25, 003, NULL, 'Uma maquina a vapor que gera energia através da queima do carvão que gera vapor.', 'Maquina a vapor'),
(006, 17500, 32, 004, 005, 'Uma usina que gera energia através da água.', 'Usina Hidrelétrica'),
(007, 30000, 40, 004, 005, 'Uma maquina que gera energia através das forças do vento.', 'Aerogeradores'),
(008, 47500, 70, 004, NULL, '', 'Maquina a vapor v2'),
(009, 60000, 90, 005, NULL, 'Uma maquina que produz energia nuclear.', 'Maquina Nuclear'),
(010, 75000, 100, 005, NULL, '', 'Maquina a vapor v3'),
(011, 80000, 122, 005, NULL, 'Maquina que produz energia com o auxilio do sol.', 'Energia Solar'),
(012, 125000, 150, 006, NULL, '', 'Antimatéria'),
(013, 180000, 180, 006, NULL, 'Maquina que produz energia com o auxilio do sol.', 'Energia Solar v2'),
(014, 220000, 210, 006, NULL, 'Uma maquina que produz energia nuclear.', 'Maquina Nuclear v2');

-- --------------------------------------------------------

--
-- Estrutura para tabela `pesquisas`
--

CREATE TABLE `pesquisas` (
  `id` int(10) UNSIGNED NOT NULL,
  `pesquisa` varchar(45) NOT NULL,
  `tempo` time NOT NULL COMMENT 'Tempo que a pesquisa demora para ficar pronta.',
  `valor` int(10) UNSIGNED NOT NULL COMMENT 'Valor da pesquisa em energia.',
  `fase` smallint(5) UNSIGNED NOT NULL COMMENT 'fase que a pesquisa esta disponível.',
  `mudaFase` smallint(5) UNSIGNED DEFAULT NULL COMMENT 'informa se a pesquisa muda de fase ou não: null não muda \n 1 muda'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Fazendo dump de dados para tabela `pesquisas`
--

INSERT INTO `pesquisas` (`id`, `pesquisa`, `tempo`, `valor`, `fase`, `mudaFase`) VALUES
(1, 'Moinho de vento', '00:10:00', 10000, 1, 1),
(2, 'Moinho de agua', '00:20:00', 20000, 2, NULL);

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `login` varchar(20) NOT NULL COMMENT 'A coluna login armazena o login dos usuários, os logins são únicos e não podem repetir-se.',
  `senha` char(32) NOT NULL COMMENT 'Armazena a senha do usuário.',
  `nome` varchar(45) NOT NULL COMMENT 'Armazena o nome do usuário.',
  `email` varchar(85) NOT NULL COMMENT 'Armazena o email do usuário.',
  `ultimo_acesso` date NOT NULL COMMENT 'Armazena a data do último acesso do usuário.',
  `permissao` tinyint(1) UNSIGNED NOT NULL COMMENT 'Armazena a permissão do usuário:\n\n0 para Visitante,\n1 para Jogador e\n2 para Administrador.',
  `nascimento` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='A tabela "usuarios" é responsável por armazenar os dados referentes aos usuários cadastrados em geral, administradores e jogadores, estes são diferenciados pelo nível da permissão deles. Dependendo dela, os usuários são redirecionados para diferentes páginas, sendo elas a index_adm e a indexJ.';

--
-- Fazendo dump de dados para tabela `usuarios`
--

INSERT INTO `usuarios` (`login`, `senha`, `nome`, `email`, `ultimo_acesso`, `permissao`, `nascimento`) VALUES
('123', '202CB962AC59075B964B07152D234B70', '123', '123', '2018-07-30', 2, '1200-12-12'),
('1234', '202CB962AC59075B964B07152D234B70', '1234', '1234', '2018-09-07', 1, '1200-12-12'),
('12345', '202CB962AC59075B964B07152D234B70', 'nada', '12345', '2018-08-22', 2, '1200-12-12'),
('nada', '202CB962AC59075B964B07152D234B70', 'nada', 'nada.pont.com@gmail.com', '2018-08-06', 2, '1200-12-12'),
('vini', '202CB962AC59075B964B07152D234B70', 'vini', 'vini', '2018-08-06', 2, '1200-12-12');

--
-- Índices de tabelas apagadas
--

--
-- Índices de tabela `baterias`
--
ALTER TABLE `baterias`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_clientes_usuarios1_idx` (`usuarios_login`);

--
-- Índices de tabela `clientes_tem_baterias`
--
ALTER TABLE `clientes_tem_baterias`
  ADD PRIMARY KEY (`clientes_id`,`baterias_id`),
  ADD KEY `fk_clientes_has_Baterias_Baterias1_idx` (`baterias_id`),
  ADD KEY `fk_clientes_has_Baterias_clientes1_idx` (`clientes_id`);

--
-- Índices de tabela `clientes_tem_maquinas`
--
ALTER TABLE `clientes_tem_maquinas`
  ADD PRIMARY KEY (`clientes_id`,`maquinas_id`),
  ADD KEY `fk_clientes_has_maquinas1_maquinas1_idx` (`maquinas_id`),
  ADD KEY `fk_clientes_has_maquinas1_clientes1_idx` (`clientes_id`);

--
-- Índices de tabela `clientes_tem_pesquisas`
--
ALTER TABLE `clientes_tem_pesquisas`
  ADD PRIMARY KEY (`clientes_id`,`pesquisas_id`),
  ADD KEY `fk_clientes_has_Pesquisa_Pesquisa1_idx` (`pesquisas_id`),
  ADD KEY `fk_clientes_has_Pesquisa_clientes1_idx` (`clientes_id`);

--
-- Índices de tabela `denuncias`
--
ALTER TABLE `denuncias`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Denuncias_clientes1_idx` (`clientes_id`),
  ADD KEY `fk_Denuncias_usuarios1_idx` (`usuarios_login`);

--
-- Índices de tabela `fases`
--
ALTER TABLE `fases`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_fases_clientes1_idx` (`clientes_id`);

--
-- Índices de tabela `maquinas`
--
ALTER TABLE `maquinas`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `pesquisas`
--
ALTER TABLE `pesquisas`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`login`);

--
-- AUTO_INCREMENT de tabelas apagadas
--

--
-- AUTO_INCREMENT de tabela `baterias`
--
ALTER TABLE `baterias`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de tabela `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(10) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT 'Armazena o id do cliente, cada id é único.', AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT de tabela `denuncias`
--
ALTER TABLE `denuncias`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de tabela `fases`
--
ALTER TABLE `fases`
  MODIFY `id` int(10) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT 'Armazena o id da fase, ele é único.';
--
-- AUTO_INCREMENT de tabela `maquinas`
--
ALTER TABLE `maquinas`
  MODIFY `id` tinyint(3) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT 'Armazena o id das máquinas, cada id é único.', AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT de tabela `pesquisas`
--
ALTER TABLE `pesquisas`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Restrições para dumps de tabelas
--

--
-- Restrições para tabelas `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `fk_clientes_usuarios1` FOREIGN KEY (`usuarios_login`) REFERENCES `usuarios` (`login`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `clientes_tem_baterias`
--
ALTER TABLE `clientes_tem_baterias`
  ADD CONSTRAINT `fk_clientes_has_Baterias_Baterias1` FOREIGN KEY (`baterias_id`) REFERENCES `baterias` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_clientes_has_Baterias_clientes1` FOREIGN KEY (`clientes_id`) REFERENCES `clientes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `clientes_tem_maquinas`
--
ALTER TABLE `clientes_tem_maquinas`
  ADD CONSTRAINT `fk_clientes_has_maquinas1_clientes1` FOREIGN KEY (`clientes_id`) REFERENCES `clientes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_clientes_has_maquinas1_maquinas1` FOREIGN KEY (`maquinas_id`) REFERENCES `maquinas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `clientes_tem_pesquisas`
--
ALTER TABLE `clientes_tem_pesquisas`
  ADD CONSTRAINT `fk_clientes_has_Pesquisa_Pesquisa1` FOREIGN KEY (`pesquisas_id`) REFERENCES `pesquisas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_clientes_has_Pesquisa_clientes1` FOREIGN KEY (`clientes_id`) REFERENCES `clientes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `denuncias`
--
ALTER TABLE `denuncias`
  ADD CONSTRAINT `fk_Denuncias_clientes1` FOREIGN KEY (`clientes_id`) REFERENCES `clientes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Denuncias_usuarios1` FOREIGN KEY (`usuarios_login`) REFERENCES `usuarios` (`login`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `fases`
--
ALTER TABLE `fases`
  ADD CONSTRAINT `fk_fases_clientes1` FOREIGN KEY (`clientes_id`) REFERENCES `clientes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
