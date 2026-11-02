package Tasks;

import Dados.Conta;
import Framework.Utils.JsUtils;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import Validacao.LoginValidacao;
import org.openqa.selenium.WebDriver;

public class LoginTask {

    private final WebDriver driver;
    private final LoginPage loginPage;
    private final HomePage homePage;
    private final LoginValidacao loginValidacao;

    public LoginTask(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(this.driver);
        homePage = new HomePage(this.driver);
        loginValidacao = new LoginValidacao(this.driver);
    }

    public void efetuarLogin(Conta conta) {
        JsUtils.digitar(loginPage.getEmailInput(), conta.getEmail());
        JsUtils.digitar(loginPage.getPasswordInput(), conta.getSenha());
        JsUtils.clicarSeguro(driver, loginPage.getAcessarButton());
        loginValidacao.validacaoUsuarioLogado(conta.getNome());
    }

    public void efetuarLogout() {
        JsUtils.clicarSeguro(driver, homePage.getSairButton());
        loginValidacao.validacaoTelaLoginExibida();
    }
}
