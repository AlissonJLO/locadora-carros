-- Criação das tabelas

-- Tabela de Usuário do Sistema
CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    login VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,  -- A senha deve ser armazenada como um hash
    nivel_acesso VARCHAR(20) NOT NULL CHECK (nivel_acesso IN ('admin', 'user'))
);


CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    endereco VARCHAR(150),
    telefone VARCHAR(15)
);

CREATE TABLE carro (
    id SERIAL PRIMARY KEY,
    modelo VARCHAR(50) NOT NULL,
    fabricante VARCHAR(50) NOT NULL,
    ano INTEGER,
    placa VARCHAR(20) UNIQUE NOT NULL,
    disponibilidade BOOLEAN NOT NULL
);

CREATE TABLE reserva (
    id SERIAL PRIMARY KEY,
    data_reserva DATE NOT NULL,
    data_devolucao DATE,
    valor NUMERIC(10, 2),
    data_pagamento DATE,
    cliente_id INTEGER REFERENCES Cliente(id),
    carro_id INTEGER REFERENCES Carro(id)
);

CREATE TABLE manutencao (
    id SERIAL PRIMARY KEY,
    data_manutencao DATE NOT NULL,
    descricao TEXT,
    valor NUMERIC(10, 2),
    carro_id INTEGER REFERENCES Carro(id)
);

CREATE TABLE seguro (
    id SERIAL PRIMARY KEY,
    tipo_seguro VARCHAR(50),
    valor_seguro NUMERIC(10, 2),
    carro_id INTEGER REFERENCES Carro(id)
);

CREATE TABLE multa (
    id SERIAL PRIMARY KEY,
    valor NUMERIC(10, 2),
    data_multa DATE,
    descricao TEXT,
    reserva_id INTEGER REFERENCES Reserva(id)  -- Chave estrangeira para Reserva
);

CREATE TABLE avaliacao (
    id SERIAL PRIMARY KEY,
    nota INTEGER CHECK (nota >= 1 AND nota <= 5),
    comentario TEXT,
    cliente_id INTEGER REFERENCES Cliente(id),
    carro_id INTEGER REFERENCES Carro(id)
);


INSERT INTO usuario (nome, login, senha, nivel_acesso)
VALUES ('Usuário Padrão', 'usuario', 'senha123', 'user');

INSERT INTO usuario (nome, login, senha, nivel_acesso)
VALUES ('Administrador', 'admin', 'admin123', 'admin');
