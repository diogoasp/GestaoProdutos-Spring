-- Adcionando usuário padrão. 
-- Login: admin
-- Senha: admin
INSERT INTO usuario (login, senha) values ('admin','$2y$12$CoiHuD4z1Q.s4FN1ByqzEu7AM4dT2eMslmsjBCu5njvTGlSq4tEaK');
INSERT INTO permissao (nome) values ('ADMIN'),('COMUM');
INSERT INTO usuario_permissoes (usuarios_id, permissoes_id) values (1,1);

-- Entrada de produtos para teste
INSERT INTO produto (id,categoria, dt_validade, foto, nome, preco, unidade_medida) values (1,'biscoito','2020-05-06','/files/jpg/img1.jpg','Waffer Bauducco',45.95,'un');