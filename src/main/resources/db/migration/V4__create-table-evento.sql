CREATE TABLE evento (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nome VARCHAR(255) NOT NULL,
                        tipo VARCHAR(50),
                        descricao TEXT,
                        chave_pix VARCHAR(255),
                        data_inicio DATETIME NOT NULL,
                        data_fim DATETIME NOT NULL,
                        quantidade_participantes INT NULL,
                        recorrente BOOLEAN DEFAULT FALSE,
                        frequencia VARCHAR(20),
                        dia_semana INT,
                        intervalo INT,
                        vagas_total INT NULL,
                        vagas_disponiveis INT,
                        data_inicio_recorrencia DATETIME,
                        data_fim_recorrencia DATETIME
);
