-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: Aug 31, 2020 at 10:51 PM
-- Server version: 8.0.21
-- PHP Version: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tpack`
--

-- --------------------------------------------------------

--
-- Table structure for table `ANSWER`
--

CREATE TABLE `ANSWER` (
  `ID` bigint NOT NULL,
  `ANSWER` varchar(255) DEFAULT NULL,
  `QUESTION_ID` bigint DEFAULT NULL,
  `SUBJECT_ID` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `CHOICE`
--

CREATE TABLE `CHOICE` (
  `ID` bigint NOT NULL,
  `TEXT` varchar(255) DEFAULT NULL,
  `QUESTION_ID` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `CHOICE`
--

INSERT INTO `CHOICE` (`ID`, `TEXT`, `QUESTION_ID`) VALUES
(1, 'Municipal', 29),
(2, 'Estadual', 29),
(3, 'Federal', 29),
(4, 'Privada ', 29),
(5, 'Fundamental A', 30),
(6, 'Fundamental B', 30),
(7, 'Médio', 30),
(8, 'Técnico', 30),
(9, 'Tecnológico ou Superior', 30),
(10, 'Pós-graduação', 30),
(11, 'Fundamental', 31),
(12, 'Médio', 31),
(13, 'Magistério', 31),
(14, 'Técnico', 31),
(15, 'Tecnólogo', 31),
(16, 'Bacharel', 31),
(17, 'Licenciado', 31),
(18, 'Mestrado', 31),
(19, 'Doutorado', 31),
(20, 'Pós-doutorado', 31),
(21, 'Ciências agrárias', 33),
(22, 'Ciências biológicas', 33),
(23, 'Ciências da saúde', 33),
(24, 'Ciências exatas e da terra', 33),
(25, 'Ciências humanas', 33),
(26, 'Ciências sociais aplicadas', 33),
(27, 'Engenharias', 33),
(28, 'Linguística, letras e artes', 33),
(29, 'Salas de Vídeo conferência ', 38),
(30, 'Mídias sociais ', 38),
(31, 'Ambientes Virtuais de Aprendizagem ', 38),
(32, 'Ferramentas de comunicação ', 38),
(33, 'Nenhuma', 38),
(34, 'Masculino', 39),
(35, 'Feminino', 39),
(36, 'Outros', 39),
(37, 'Prefiro não informar', 39);

-- --------------------------------------------------------

--
-- Table structure for table `QUESTION`
--

CREATE TABLE `QUESTION` (
  `ID` bigint NOT NULL,
  `TEXT` varchar(255) DEFAULT NULL,
  `TYPE` int DEFAULT NULL,
  `SESSION_ID` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `QUESTION`
--

INSERT INTO `QUESTION` (`ID`, `TEXT`, `TYPE`, `SESSION_ID`) VALUES
(1, 'Posso adaptar minha abordagem de ensino com base no que os estudantes atualmente entendem', 1, 2),
(2, 'Posso adaptar meu método de ensino para diferentes estudantes', 1, 2),
(3, 'Posso usar uma ampla variedade de abordagens de ensino em sala de aula', 1, 2),
(4, 'Posso avaliar a aprendizagem dos estudantes de várias maneiras', 1, 2),
(5, 'Tenho conhecimento suficiente sobre o meu conteúdo de ensino', 1, 3),
(6, 'Posso usar uma maneira específica de pensar no meu conteúdo de ensino', 1, 3),
(7, 'Conheço as teorias e conceitos básicos do meu conteúdo de ensino', 1, 3),
(8, 'Conheço a história e o desenvolvimento de teorias importantes do meu conteúdo de ensino', 1, 3),
(9, 'Acompanho novas tecnologias importantes', 1, 4),
(10, 'Frequentemente uso tecnologia', 1, 4),
(11, 'Conheço muitas tecnologias diferentes', 1, 4),
(12, 'Tenho habilidades técnicas necessárias para usar tecnologia', 1, 4),
(13, 'Eu sei como selecionar abordagens de ensino eficazes para orientar o pensamento e a aprendizagem do meu conteúdo de ensino', 1, 5),
(14, 'Eu sei como desenvolver tarefas apropriadas para possibilitar aos estudantes um pensamento complexo do meu conteúdo de ensino', 1, 5),
(15, 'Eu sei como desenvolver exercícios com os quais os estudantes podem consolidar seus conhecimentos sobre o meu conteúdo de ensino', 1, 5),
(16, 'Eu sei como avaliar o desempenho dos estudantes no meu conteúdo de ensino ', 1, 5),
(17, 'Posso escolher tecnologias que aprimorem as abordagens de ensino para uma aula', 1, 6),
(18, 'Posso escolher tecnologias que aprimoram o aprendizado dos estudantes para uma aula', 1, 6),
(19, 'Posso adaptar o uso das tecnologias que estou aprendendo a diferentes atividades de ensino', 1, 6),
(20, 'Estou pensando criticamente sobre como usar tecnologia na minha sala de aula', 1, 6),
(21, 'Eu sei como os desenvolvimentos tecnológicos mudaram a área do meu assunto', 1, 7),
(22, 'Posso explicar quais tecnologias foram usadas em pesquisas na minha área', 1, 7),
(23, 'Eu sei quais novas tecnologias estão sendo desenvolvidas atualmente na área do meu assunto', 1, 7),
(24, 'Eu sei como usar tecnologias para participar de debates científicos na minha área', 1, 7),
(25, 'Posso usar estratégias que combinam conteúdo, tecnologias e abordagens de ensino que aprendi em meus cursos na sala de aula', 1, 8),
(26, 'Posso escolher tecnologias que aprimoram o conteúdo de uma aula', 1, 8),
(27, 'Posso selecionar tecnologias para uso em minha sala de aula que aprimoram o que eu ensino, como ensino e o que os estudantes aprendem', 1, 8),
(28, 'Posso ensinar lições que combinem adequadamente o meu conteúdo de ensino, tecnologias e abordagens de ensino', 1, 8),
(29, 'Qual o âmbito de instituição de ensino que você leciona? (pode marcar mais de uma)', 2, 1),
(30, 'Qual o nível de ensino que você atua? (pode marcar mais de um)', 2, 1),
(31, 'Sobre a sua formação, assinale as concluídas:', 2, 1),
(32, 'Indique quando foi realizada a sua última formação?', 4, 1),
(33, 'Qual área da sua última formação?', 3, 1),
(34, 'A exposição durante o período pandêmico a Tecnologia Digital de Informação e Comunicação alterou significativamente as suas atividades pedagógicas', 1, 9),
(35, 'As Tecnologias Digitais de Informação e Comunicação utilizadas na prática docente, proporcionaram uma melhoria nos processos pedagógicos', 1, 9),
(36, 'As Tecnologias Digitais de Informação e Comunicação utilizadas na prática docente, proporcionaram um maior engajamento dos estudantes de alguma forma', 1, 9),
(37, 'Você acredita que alguma de suas práticas pedagógicas no pós-pandemia permaneceriam com as alterações tecnológicas realizadas durante o período de pandemia?', 1, 9),
(38, 'Assinale as opções de ferramentas online utilizadas em parceria com seus alunos durante a pandemia que você pretende continuar utilizando no pós-pandemia. (Marque todas utilizadas)', 2, 9),
(39, 'Gênero', 3, 10),
(40, 'Idade (em anos):', 5, 10),
(41, 'Região: estado e município', 6, 10);

-- --------------------------------------------------------

--
-- Table structure for table `SEQUENCE`
--

CREATE TABLE `SEQUENCE` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `SEQUENCE`
--

INSERT INTO `SEQUENCE` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', '0');

-- --------------------------------------------------------

--
-- Table structure for table `SESSION`
--

CREATE TABLE `SESSION` (
  `ID` bigint NOT NULL,
  `TITLE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `SESSION`
--

INSERT INTO `SESSION` (`ID`, `TITLE`) VALUES
(1, 'Caracterização das Atividades'),
(2, 'Classifique as seguintes informações'),
(3, 'Avalie as seguintes afirmações em relação ao conteúdo em que você escreveu o plano de aula'),
(4, 'Avalie as seguintes afirmações sobre tecnologias digitais (computadores, tablets, telefones celulares, Internet etc.)'),
(5, 'Avalie as seguintes afirmações com relação ao ensino em que você não usa nenhuma tecnologia ou mídia especial'),
(6, 'Classifique as seguintes afirmações'),
(7, 'Avalie as seguintes afirmações em relação ao conteúdo em que você escreveu o plano de aula'),
(8, 'Avalie as seguintes afirmações em relação ao conteúdo em que você escreveu o plano de aula'),
(9, 'Uso de TDIC'),
(10, 'Caracterização do respondente');

-- --------------------------------------------------------

--
-- Table structure for table `SUBJECT`
--

CREATE TABLE `SUBJECT` (
  `ID` bigint NOT NULL,
  `FORMATION` varchar(255) DEFAULT NULL,
  `MOMENT` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ANSWER`
--
ALTER TABLE `ANSWER`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_ANSWER_QUESTION_ID` (`QUESTION_ID`),
  ADD KEY `FK_ANSWER_SUBJECT_ID` (`SUBJECT_ID`);

--
-- Indexes for table `CHOICE`
--
ALTER TABLE `CHOICE`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_CHOICE_QUESTION_ID` (`QUESTION_ID`);

--
-- Indexes for table `QUESTION`
--
ALTER TABLE `QUESTION`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_QUESTION_SESSION_ID` (`SESSION_ID`);

--
-- Indexes for table `SEQUENCE`
--
ALTER TABLE `SEQUENCE`
  ADD PRIMARY KEY (`SEQ_NAME`);

--
-- Indexes for table `SESSION`
--
ALTER TABLE `SESSION`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `SUBJECT`
--
ALTER TABLE `SUBJECT`
  ADD PRIMARY KEY (`ID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ANSWER`
--
ALTER TABLE `ANSWER`
  ADD CONSTRAINT `FK_ANSWER_QUESTION_ID` FOREIGN KEY (`QUESTION_ID`) REFERENCES `QUESTION` (`ID`),
  ADD CONSTRAINT `FK_ANSWER_SUBJECT_ID` FOREIGN KEY (`SUBJECT_ID`) REFERENCES `SUBJECT` (`ID`);

--
-- Constraints for table `CHOICE`
--
ALTER TABLE `CHOICE`
  ADD CONSTRAINT `FK_CHOICE_QUESTION_ID` FOREIGN KEY (`QUESTION_ID`) REFERENCES `QUESTION` (`ID`);

--
-- Constraints for table `QUESTION`
--
ALTER TABLE `QUESTION`
  ADD CONSTRAINT `FK_QUESTION_SESSION_ID` FOREIGN KEY (`SESSION_ID`) REFERENCES `SESSION` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
