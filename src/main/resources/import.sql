INSERT INTO tb_usuario (nome, login, senha) VALUES ('marcos', 'marcos.andre', '$2a$10$gTAvg4jWiSRdq/E9VGpGaOV/CLV4PnnR1rSBOxyqBfgzdE4IVb2Ne');

INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');
INSERT INTO tb_role (authority) VALUES ('ROLE_USER');

INSERT INTO tb_usuario_role (usuario_id, role_id) VALUES (1, 1);
INSERT INTO tb_usuario_role (usuario_id, role_id) VALUES (1, 2);