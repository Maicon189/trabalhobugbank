package TestCases;

import Dados.Conta;
import Framework.TestBase;
import Framework.Utils.FileOperations;
import Tasks.CadastroTask;
import Tasks.ExtratoTask;
import Tasks.LoginTask;
import Tasks.TransferenciaTask;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

@Epic("BugBank")
@Feature("Transferência entre contas")
public class RealizarTransferenciaComSucessoTest extends TestBase {

    private final WebDriver driver = getDriverManager();

    CadastroTask cadastroTask = new CadastroTask(driver);
    LoginTask loginTask = new LoginTask(driver);
    TransferenciaTask transferenciaTask = new TransferenciaTask(driver);
    ExtratoTask extratoTask = new ExtratoTask(driver);

    @Test
    @Story("Usuário transfere valor entre duas contas com saldo")
    @Description("Cadastra duas contas com saldo, transfere da origem para o destino e valida o extrato das duas contas")
    public void realizarTransferencia() {

        Properties dados = FileOperations.getProperties("contas");
        double valor = Double.parseDouble(dados.getProperty("transferencia.valor"));
        String descricao = dados.getProperty("transferencia.descricao");

        Conta contaOrigem = Conta.origem();
        Conta contaDestino = Conta.destino();

        // 1. Cadastra as duas contas, ambas com saldo
        cadastroTask.cadastrarConta(contaOrigem);
        cadastroTask.cadastrarConta(contaDestino);

        // 2. Conta de origem: transfere e valida o extrato (saldo debitado)
        loginTask.efetuarLogin(contaOrigem);
        transferenciaTask.realizarTransferencia(contaOrigem, contaDestino, valor, descricao);
        extratoTask.validarExtrato(contaOrigem, descricao, valor);
        loginTask.efetuarLogout();

        // 3. Conta de destino: valida o extrato (valor recebido)
        loginTask.efetuarLogin(contaDestino);
        extratoTask.validarExtrato(contaDestino, descricao, valor);
        loginTask.efetuarLogout();
    }
}
