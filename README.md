Sistema Andre Batista

Descrição

Este projeto é um sistema de gestão desenvolvido em Java, focado no gerenciamento de dados de empresas. Ele inclui funcionalidades para cadastro de empresas, login de usuários e integração com banco de dados PostgreSQL. O sistema possui uma interface gráfica de usuário (GUI) moderna, desenvolvida com Java Swing, e incorpora recursos como consulta de CEP.

Funcionalidades

•
Autenticação de Usuários: Sistema de login para acesso seguro.

•
Cadastro de Empresas: Interface para registrar e gerenciar informações detalhadas de empresas (Razão Social, CNPJ, Inscrição Estadual, Endereço, Contato, etc.).

•
Consulta de CEP: Integração para preenchimento automático de dados de endereço a partir de um CEP.

•
Gerenciamento de Banco de Dados: Conexão e operações com um banco de dados PostgreSQL para persistência dos dados.

•
Interface Gráfica Moderna: Utilização de componentes Swing personalizados para uma experiência de usuário aprimorada.

Tecnologias Utilizadas

•
Linguagem de Programação: Java

•
Interface Gráfica: Java Swing (com componentes personalizados como ModernTabbedPaneUI)

•
Banco de Dados: PostgreSQL

•
Conectividade com Banco de Dados: JDBC

•
API de Consulta de CEP: (A ser especificada, presumindo uma API externa baseada em ConsultaCep.java)

Pré-requisitos

Para executar este projeto, você precisará ter instalado:

•
Java Development Kit (JDK) 8 ou superior

•
Apache Maven (para gerenciamento de dependências, se aplicável) ou um ambiente de desenvolvimento como NetBeans/IntelliJ IDEA com suporte a projetos Java

•
Servidor PostgreSQL em execução

Instalação

Siga os passos abaixo para configurar e executar o projeto localmente:

1.
Clone o repositório:

2.
Configuração do Banco de Dados PostgreSQL:

•
Crie um banco de dados chamado postgres (ou o nome configurado em ControleDb.java).

•
Certifique-se de que o usuário postgres (ou o usuário configurado) tenha as permissões necessárias e a senha andre170190 (ou a senha configurada).

•
Execute os scripts SQL para criar as tabelas necessárias (se houver, não foram encontrados no repositório).



3.
Configuração do Projeto (IDE - Ex: NetBeans):

•
Abra o projeto em sua IDE Java preferida.

•
Adicione as bibliotecas JDBC do PostgreSQL ao classpath do projeto.

•
Verifique e ajuste as configurações de conexão com o banco de dados no arquivo src/control/ControleDb.java se necessário.



4.
Compilação e Execução:

•
Compile o projeto utilizando sua IDE.

•
Execute a classe principal (provavelmente src/View/LoginScreenModerno.java ou src/View/MenuDemo.java).



Como Contribuir

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues para relatar bugs ou sugerir melhorias, e enviar pull requests com suas modificações.

Licença

Este projeto está licenciado sob a licença MIT. Consulte o arquivo LICENSE (se disponível) para mais detalhes.

Autor

Andre Batista



