CREATE TABLE pagamento (
    id VARCHAR(255) PRIMARY KEY,
    id_usuario VARCHAR(255),
    tipo VARCHAR(50) NOT NULL,
    valor DECIMAL(19, 2) NOT NULL,
    vencimento DATE NOT NULL,
    status VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);