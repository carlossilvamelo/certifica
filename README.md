# certificates

Aplicação para envio de sertificados

## Executando a plicação
No link abaixo segue o download de um tomcat configurado com a aplicação, após o download certifique-se de que as variáveis de sistema JAVA_HOME e JRE_HOME estam configuradas, descompacte o arquivo tomcat.rar, através do terminal busque a pasta descopactada "/tomcat", entre na pasta /bin e execute o comando "catalina start", o tomcat será iniciado juntamente com a aplicação.

### Tecnologias utilizadas
- Front-end: jquery + html, css e javascript com bootstrap.
- Back-end: spring framework com as dependências a seguir: web e thymeleaf. 

### Status de implementação
camada de apresentação: requisitos concluídos
Camada de serviço: 
- O encapsulamento da chave não foi feito como solicitado, para não expor a chave da API da even3, criei uma própria para uso do sendGrid. Vi esse requisito e por questões de tempo acabem decidindo por isso, estou no meio de um processo de mudança de Natal para Recife, sem desculpas, apenas explicando a situação.
- O envio do email com plano de fundo assim como mostrado na view não foi implementado, perdi um bom tempo tentando aprender como se fazia e acabei decidindo por avançar sem o requisito.
- Apenas o teste unitário de tratamento das tags foi realizado.


ps: Estou tendo problemas com minha chave da API.
