
create table ListaCompras(id bigint not null auto_increment, dataListaCompras varchar(255), idListaCompras varchar(255), nomeListaCompras varchar(255), qtdProduto varchar(255), usuario_id bigint, primary key (id)) engine=InnoDB;

create table Produto (id bigint not null auto_increment, dataProduto varchar(255), descricao varchar(255), marca varchar(255), mercado varchar(255), nome varchar(255), pego varchar(255), quantidade varchar(255), valorProduto varchar(255), compra_id bigint, primary key (id)) engine=InnoDB;

create table Usuario (id bigint not null auto_increment, celular varchar(255), cep varchar(255), cpf varchar(255), email varchar(255), login varchar(255), nome varchar(255), perfil varchar(255), senha varchar(255), primary key (id)) engine=InnoDB;

alter table ListaCompras add constraint FK8hl3qwxxt0x3c8bx9g11godo1 foreign key (usuario_id) references Usuario (id);

alter table Produto add constraint FKolb7be6n8sbkx0yfcdiucbw4b foreign key (compra_id) references ListaCompras (id);
