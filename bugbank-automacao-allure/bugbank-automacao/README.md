# Automação BugBank - Java + Selenium + JUnit 5 + Allure

Prova final de Automação Web. Site testado: https://bugbank.netlify.app/

Fluxo: cadastra 2 contas com saldo -> transfere da conta A para a B -> valida o extrato da A -> valida o extrato da B.

## Estrutura
```
src/main/java/Framework
    Browser/   DriverManager, TypeBrowser, Waits
    Utils/     FileOperations, FakersGeneration, TakeScreenshot, JsUtils
    TestBase
src/main/resources/Properties
    url.properties      (endereço do site)
    contas.properties   (dados das contas e da transferência)
src/test/java
    PageObjects/  mapeamento dos elementos (ver mapeamento.txt)
    Tasks/        passos de negócio (cadastrar, logar, transferir, extrato)
    Validacao/    asserts (com print anexado ao Allure)
    Dados/        Conta e Moeda
    TestCases/    teste final
src/test/resources/allure.properties
```

## Como rodar no IntelliJ
1. File > Open > selecione o `pom.xml` > Open as Project. Aguarde o Maven baixar as dependências.
2. Project Structure > SDK: JDK 21 ou superior (o projeto compila para a versão 21).
3. Abra `TestCases/RealizarTransferenciaComSucessoTest` e clique no play verde.

## Relatório Allure
1. Rode os testes (play verde ou `mvn clean test`). Os resultados vão para `target/allure-results`.
2. Para abrir o relatório, use um destes:
   - Maven: `mvn allure:serve` (na primeira vez baixa o Allure, então precisa de internet)
   - Allure instalado: `allure serve target/allure-results`

O relatório mostra os passos, o Epic/Feature/Story do teste e os prints tirados nas validações
(e um print "ERRO" se o teste falhar).

## Lições aprendidas
- **Espera explícita:** o BugBank é React; os elementos aparecem de forma assíncrona. A `BasePage` usa `WebDriverWait`.
- **ElementClickInterceptedException:** botões cobertos por outro elemento. Solução: `JsUtils.clicarSeguro`.
- **Formulário que mantém estado:** o cadastro não limpa os campos entre uma conta e outra. Solução: `JsUtils.digitar` e voltar ao login após cada cadastro.
- **Toggle customizado:** `isSelected()` não reflete o estado real. Solução: o próprio código controla o estado (`toggleLigado` no `CadastroTask`).
- **Não engolir erros:** asserts sem `try/catch`, para o teste falhar de verdade.
- **Dados fora do código:** URL e contas ficam em `src/main/resources/Properties`.
