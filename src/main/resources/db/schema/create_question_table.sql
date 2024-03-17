CREATE TABLE `question` (
  `id` bigint NOT NULL,
  `ansa` varchar(255) DEFAULT NULL,
  `ansb` varchar(255) DEFAULT NULL,
  `ansc` varchar(255) DEFAULT NULL,
  `ansd` varchar(255) DEFAULT NULL,
  `category` enum('JAVA','LINUX','GIT','SPRING','VARIOUS','SQL') DEFAULT NULL,
  `good_answer` varchar(255) DEFAULT NULL,
  `level` enum('EASY','MEDIUM','HARD') DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `explanation` varchar(2000) NOT NULL DEFAULT 'Explanation has not been provided',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
