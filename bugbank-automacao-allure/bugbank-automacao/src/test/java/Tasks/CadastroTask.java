package Tasks;

import Dados.Conta;
import Framework.Utils.JsUtils;
import PageObjects.CadastroPage;
import PageObjects.GenericPage;
import PageObjects.LoginPage;
import Validacao.GenericValidacao;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroTask {

    private final WebDriver driver;
    private final LoginPage loginPage;
    private final CadastroPage cadastroPage;
    private final GenericPage genericPage;
    private final GenericValidacao genericValidacao;


    private boolean toggleLigado = false;

    public CadastroTask(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(this.driver);
        cadastroPage = new CadastroPage(this.driver);
        genericPage = new GenericPage(this.driver);
        genericValidacao = new GenericValidacao(this.driver);
    }

    public void cadastrarConta(Conta conta) {

        if (!cadastroPage.isFormularioVisivel()) {
            JsUtils.clicarSeguro(driver, loginPage.getRegistrarButton());
        }

        JsUtils.digitar(cadastroPage.getEmailInput(), conta.getEmail());
        JsUtils.digitar(cadastroPage.getNomeInput(), conta.getNome());
        JsUtils.digitar(cadastroPage.getPasswordInput(), conta.getSenha());
        JsUtils.digitar(cadastroPage.getPasswordConfirmationInput(), conta.getSenha());

        if (conta.isComSaldo() != toggleLigado) {
            JsUtils.clicar(driver, cadastroPage.getCriarContaComSaldoToggle());
            toggleLigado = conta.isComSaldo();
        }

        JsUtils.clicarSeguro(driver, cadastroPage.getCadastrarButton());

        genericValidacao.validacaoModalContem("criada com sucesso");
        guardarNumeroDaConta(conta, genericPage.getModalText().getText());
        genericPage.getModalCloseButton().click();

        JsUtils.clicarSeguro(driver, cadastroPage.getVoltarLoginLink());
    }


    private void guardarNumeroDaConta(Conta conta, String mensagem) {
        Matcher matcher = Pattern.compile("(\\d+)-(\\d+)").matcher(mensagem);
        Assertions.assertTrue(matcher.find(),
                "Nao foi possivel encontrar o numero da conta na mensagem: '" + mensagem + "'");
        conta.setNumeroConta(matcher.group(1), matcher.group(2));
    }
}
