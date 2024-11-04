CREATE DATABASE cadastro_academico;

USE cadastro_academico;

CREATE TABLE curso(
    nome varchar(40) NOT NULL,
    cod int(2) NOT NULL,
    carga int(2),
    cod_inst int(2) NOT NULL,
    tipo_curso varchar(40) NOT NULL,
    PRIMARY KEY(cod)
);

CREATE TABLE disciplina(
    cod int(2) NOT NULL,
    nome varchar(40) NOT NULL,
    carga int(2),
    aulas_semana int(2),
    PRIMARY KEY(cod)
);

CREATE TABLE aluno(
    nome varchar(40) NOT NULL,
    matricula int(2) NOT NULL,
    dt_nasc date,
    cod_curso int(2),
    nota_np1 DECIMAL(4,2),
    nota_np2 DECIMAL(4,2),
    faltas int(2),
    cod_disciplina int(2),
    PRIMARY KEY(matricula)
);

CREATE TABLE professor(
    cod int(2) NOT NULL,
    nome varchar(40) NOT NULL,
    endereco varchar(40),
    dt_nasc date,
    especializacao varchar(20),
    titulo varchar(40),
    PRIMARY KEY(cod)
);

INSERT INTO curso VALUES(
    "Ciência da Computação",
    1,
    10,
    1,
    "Bacharel"
);

INSERT INTO disciplina VALUES(
    1,
    "ALPOO",
    60,
    2
);

INSERT INTO aluno VALUES(
    "Levi de Souza",
    1,
    "1749-05-27",
    1,
    10.0,
    9.5,
    4,
    1
);

INSERT INTO professor VALUES(
    1,
    "Marcos",
    "Rua Asdrubal, 999",
    "1985-04-26",
    "Informática",
    "Mestrado"
);
