create database posgraduacaorecommander;


use posgraduacaorecommander;


CREATE TABLE `tb_competencia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `competencia` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;


INSERT INTO `tb_competencia` (competencia) VALUES ('html'),('css'),('java'),('arquitetura'),('bancos de dados'),('testes de software'),('qualidade de software'),('infraestrutura'),('suporte técnico'),('Engenharia da Computação'),('Gerência de Projetos'),('Técnologia da informação');


CREATE TABLE `tb_usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


CREATE TABLE `tb_usuario_competencia` (
  `id_usuario` int(11) DEFAULT NULL,
  `id_competencia` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_usuario_idx` (`id_usuario`),
  KEY `fk_competencia_idx` (`id_competencia`),
  CONSTRAINT `fk_competencia` FOREIGN KEY (`id_competencia`) REFERENCES `tb_competencia` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
