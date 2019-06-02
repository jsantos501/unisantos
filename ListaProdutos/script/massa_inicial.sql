INSERT INTO `unisantos`.`usuario`
(
`login`,
`senha`,
`perfil`,
`nome`,
`cpf`,
`email`,
`cep`,
`celular`)
VALUES
('admin',
'admin',
1,
'admin',
32965055800,
'admin@unisantos.com',
11088420,
11966931360);


INSERT INTO `unisantos`.`produto`
(`pego`,
`nome`,
`marca`,
`quantidade`,
`descricao`,
`valorProduto`,
`dataProduto`,
`mercado`)
VALUES
(0,
'lata de ervilhas',
'jurema',
1,
'200g de ervilhas',
1.99,
'2019-5-12',
'hipermerdado Extra');


INSERT INTO `unisantos`.`compras`
(`nome`,
`data`,
`idUser`)
VALUES
('lista incial',
'2019-5-12',
1);

INSERT INTO `unisantos`.`compras_produtos`
(`idCompra`,
`idProduto`)
VALUES
(1,
1);
