# financial-synchronizer
### Resumo
Solução para o Desafio Técnico do Sicredi.

#### Desafio proposto
Criar uma aplicação SpringBoot standalone. Exemplo: java -jar SincronizacaoReceita <input-file>
1. Processa um arquivo CSV de entrada com o formato abaixo.
2. Envia a atualização para a Receita através do serviço (SIMULADO pela classe ReceitaService).
3. Retorna um arquivo com o resultado do envio da atualização da Receita. Mesmo formato adicionando o resultado em uma nova coluna.

#### Tecnologias utilizadas
Para esse projeto, foram utilizadas as seguintes tecnologias:
* Java 11: Linguagem utilizada no projeto;
* Spring: Inicialização da API e injeção de dependências;
* Gradle: Automação do build e gestão de dependências;
* OpenCsv: Biblioteca open source para leitura/escrita de arquivos com a extensão .csv

#### Requerimentos
Para executar o sistema, é necessário ter Java 11 instalado na máquina

#### Execução da aplicação
Fazer o build do projeto:
./gradlew clean build

Executar o sistema:
java -jar .\build\libs\financial-synchronizer-1.0.jar input-sample.csv

O arquivo input-sample.csv contém o exemplo citado na descrição do desafio.

**Importante** Os comandos descritos nesse documento devem ser executados num terminal aberto na pasta raiz do projeto.
