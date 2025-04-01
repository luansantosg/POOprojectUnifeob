CREATE DATABASE IF NOT EXISTS SistemaAvaliacaoLivros;
USE SistemaAvaliacaoLivros;

CREATE TABLE Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE Livro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL
);

CREATE TABLE Avaliacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    livro_id INT,
    nota INT CHECK (nota BETWEEN 1 AND 5),
    comentario TEXT,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (livro_id) REFERENCES Livro(id) ON DELETE CASCADE
);

INSERT INTO Usuario (nome, email) VALUES ('Luan Santos', 'luansantos@email.com');
INSERT INTO Usuario (nome, email) VALUES ('Joao do Pé de Feijao', 'joaofeijao@email.com');

INSERT INTO Livro (titulo, autor) VALUES ('Dom Casmurro', 'Machado de Assis');
INSERT INTO Livro (titulo, autor) VALUES ('1984', 'George Orwell');

INSERT INTO Avaliacao (usuario_id, livro_id, nota, comentario) VALUES (1, 1, 5, 'Ótimo livro!');
INSERT INTO Avaliacao (usuario_id, livro_id, nota, comentario) VALUES (2, 2, 4, 'Interessante e envolvente.');


SELECT l.titulo, l.autor, AVG(a.nota) AS media_nota
FROM Livro l
LEFT JOIN Avaliacao a ON l.id = a.livro_id
GROUP BY l.id;


SELECT u.nome, a.nota, a.comentario 
FROM Avaliacao a
JOIN Usuario u ON a.usuario_id = u.id
WHERE a.livro_id = 1;
