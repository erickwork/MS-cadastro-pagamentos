CREATE TABLE pagamento (
    id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_usuario bigint(20),
    tipo VARCHAR(50) NOT NULL,
    valor DECIMAL(19, 2) NOT NULL,
    vencimento DATE NOT NULL,
    status VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);