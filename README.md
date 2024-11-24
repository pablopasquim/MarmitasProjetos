Sistema de Marmitas

Integrantes 

Nome: Carlos Samuel Marcelino - usuario github AzUumy e carlos-analitica
Nome: Pablo Pasquim - usuario github pablopasquim
Nome Gustavo Toledo - usuario github
Nome William - usuario github 
Nome: Breno Andrade - usuario github 


Descrição
O projeto Sistema de Marmitas é um aplicativo Android desenvolvido para gerenciar o cadastro e pedidos de marmitas, permitindo aos usuários cadastrar, editar e visualizar informações de clientes e marmitas. Além disso, o sistema facilita a realização de pedidos, com navegação entre telas para gerenciar tanto as marmitas quanto os clientes.

Funcionalidades
Cadastro de Cliente

O usuário pode cadastrar clientes inserindo dados como nome, e-mail, endereço e preferências alimentares.
Edição de Cliente

Após cadastrar um cliente, é possível editar as informações deste cliente. O aplicativo leva o usuário para a tela de edição ao selecionar um cliente e realizar as alterações necessárias.
Visualização de Clientes

Permite que o usuário visualize todos os clientes cadastrados. Ao selecionar um cliente, ele será levado à tela de edição do cliente.
Tela de Marmitas

O aplicativo possui uma tela dedicada para gerenciar marmitas. O usuário pode:
Cadastrar novas marmitas.
Visualizar as marmitas cadastradas.
Fazer pedidos de marmitas para os clientes cadastrados.
Navegação entre as telas

A navegação entre as telas de "Clientes" e "Marmitas" é facilitada por botões de navegação no menu principal. As atividades de edição e visualização de clientes, bem como a tela de marmitas, permitem uma navegação fluída entre elas.
Instruções de Uso
1. Tela Principal (MainActivity)
Ao iniciar o aplicativo, o usuário é direcionado para a tela principal, onde existem duas opções:
Gerenciar Clientes: Leva à tela de clientes, onde o usuário pode visualizar, editar ou cadastrar novos clientes.
Gerenciar Marmitas: Leva à tela de marmitas, onde o usuário pode cadastrar, visualizar e fazer pedidos de marmitas.
2. Cadastro de Cliente
Na tela de clientes, o usuário pode cadastrar novos clientes clicando no botão Adicionar Cliente. Após preencher os dados do cliente (nome, e-mail, endereço e preferências alimentares), o cliente será adicionado à lista.
3. Edição de Cliente
Para editar um cliente existente, basta selecionar o cliente da lista e clicar na opção Editar Cliente. O usuário poderá alterar as informações do cliente e salvar as alterações.
4. Visualizar Clientes
Ao clicar na opção Visualizar Clientes, o usuário poderá ver todos os clientes cadastrados, com a opção de editar ou excluir cada cliente.
5. Cadastro de Marmita
Na tela de marmitas, o usuário pode cadastrar novas marmitas. A tela de cadastro solicita informações como nome e descrição da marmita.
6. Visualizar Marmitas
A tela de marmitas permite que o usuário visualize todas as marmitas cadastradas e acesse detalhes de cada uma.
7. Fazer Pedido
Após cadastrar as marmitas, o usuário pode realizar pedidos, selecionando as marmitas desejadas e associando-as a um cliente já cadastrado.
Navegação entre as Telas
Tela Principal (MainActivity): O ponto de entrada do aplicativo, onde o usuário pode escolher entre gerenciar clientes ou marmitas.
Tela de Clientes: Exibe a lista de clientes e permite as opções de editar ou cadastrar novos clientes.
Tela de Marmitas: Exibe a lista de marmitas e permite as opções de cadastro, visualização e pedidos.
Edição de Cliente: Tela acessada a partir da tela de clientes para editar as informações de um cliente selecionado.
Exemplo de Fluxo do Usuário
Acessando a Tela de Clientes:

O usuário clica em Gerenciar Clientes na tela principal e é levado à tela de clientes.
O usuário pode clicar em Adicionar Cliente para cadastrar um novo cliente ou clicar em um cliente existente para editar suas informações.
Editando Cliente:

O usuário edita as informações do cliente, como nome, e-mail e preferências alimentares.
Ao salvar as alterações, o cliente é atualizado na lista de clientes.
Acessando a Tela de Marmitas:

O usuário clica em Gerenciar Marmitas na tela principal e é levado à tela de marmitas.
O usuário pode cadastrar novas marmitas, visualizar as marmitas cadastradas e realizar pedidos.
Fazendo Pedido:

Após visualizar as marmitas disponíveis, o usuário escolhe uma marmita e a associa a um cliente para realizar o pedido.
Tecnologias Utilizadas
Kotlin: Linguagem de programação utilizada para o desenvolvimento do aplicativo.
Jetpack Compose: Framework para construção da interface do usuário.
Android SDK: Ferramentas e bibliotecas para o desenvolvimento de aplicativos Android.
Como Rodar o Projeto
Clone o repositório:

bash
Copy code
git clone https://github.com/seu-usuario/marmitas-app.git
Abra o projeto no Android Studio.

Conecte um dispositivo Android ou inicie um emulador.

Compile e execute o aplicativo.

