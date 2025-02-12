-- Tabela de Usuario
CREATE TABLE Usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE
);

-- Tabela de Livro
CREATE TABLE Livro (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    editora VARCHAR(255) NOT NULL,
    ano INT NOT NULL,
    is_emprestado BOOLEAN DEFAULT FALSE
);

-- Tabela de Biblioteca
CREATE TABLE Biblioteca (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

-- Tabela de Emprestimo (depende de Usuario, Livro e Biblioteca)
CREATE TABLE Emprestimo (
    id SERIAL PRIMARY KEY,
    usuario_id INT NOT NULL,
    livro_id INT NOT NULL,
    biblioteca_id INT NOT NULL,
    data_emprestimo DATE NOT NULL,
    data_devolucao DATE NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id),
    FOREIGN KEY (livro_id) REFERENCES Livro(id),
    FOREIGN KEY (biblioteca_id) REFERENCES Biblioteca(id)
);

-- Tabela de Historico_Emprestimos (depende de Usuario e Livro)
CREATE TABLE Historico_Emprestimos (
    id SERIAL PRIMARY KEY,
    usuario_id INT NOT NULL,
    livro_id INT NOT NULL,
    data_emprestimo DATE NOT NULL,
    data_devolucao DATE NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id),
    FOREIGN KEY (livro_id) REFERENCES Livro(id)
);
