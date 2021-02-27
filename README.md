# gerenciador_de_evento

Sistema de gerenciamento de evento na plataforma web, desenvolvido utilizando o framework Spring e o banco de dados MySQL para persistência de dados.

O sistema possui uma página inicial (/), onde possui uma singela tela de menu para acessar os recursos do sistema. Uma navbar também está presente em todas as telas para navegar pelo sistema.

O sistema disponibiliza recursos como cadastro, atualização e exclusão de registros de pessoas, salas e espaços para café.
Além de ações de um CRUD, também possui um recurso para alocar pessaos em salas e espaços para café, podendo ser de duas formas.
De forma automática, onde o sistema irá distribuir as pessaos de forma que as pessoas tenham contato com o maior número de pessoas. 
Ou de forma manual, quando for criado registrado uma nova pessoa, é possível atualizar seus dados e alocar.

Alguns testes unitários foram criados para testar a validação de dados, acessos a URIs, e funcionamento dos Repositories.
