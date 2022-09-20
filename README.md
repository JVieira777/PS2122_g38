# PS2122_g38
Projeto final e seminario do grupo 38

Instruções de Setup 

 

 

Construção dos projetos em Intellij build -> build project 

 

Criação de duas bases de dados em Postgressql: 

1.Criar a base de dados para o API com o nome desejado, de seguida utilizar o documento alocado no path PS2122_g38\Api\WebApp\src\main\resources\SQL  para o preenchimento da mesma. 

2. Criar a base de dados para a APP com o nome desejado, de seguida utilizar o documento alocado no path Project2022final\PS2122_g38\Example App\ClientServer\src\main\kotlin\pt\isel\WebApp\SQLDB 

para o preenchimento da mesma. 

 

Criação de três variáveis de ambiente a ser utilizadas pelos módulos do projeto que deveram ser alocadas no ficheiro application.properties: 

spring.datasource.url=${DB_URL}  

spring.datasource.username=${DB_USER} 

spring.datasource.password=${DB_PASSWORD} 

 

DB_PASSWORD= <<password>>;  

DB_URL= <<url da base de dados para a aplicação>>;  

DB_USER= <<username utilizado na base de dados>>; 

Após a criação destas três variáveis de ambiente terá de serem alteradas as configurações de execução do programa e adicionar as mesmas como o exemplo em baixo: 

DB_PASSWORD=qwer;DB_URL=jdbc:postgresql://localhost:5432/PS2122_G38_API;DB_USER=postgres;private_key=293549cce579c185c287e254e2456b723f51a599f770e645245213c56604339f 

 

Para a parte Frontend da APP utilizar o comando npm install apos este comando apenas é necessário inicializar a aplicação usando o comando npm start. 
