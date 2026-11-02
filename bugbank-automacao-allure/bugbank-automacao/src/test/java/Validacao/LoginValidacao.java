package Validacao;

import Framework.Utils.TakeScreenshot;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class LoginValidacao {

    private final WebDriver driver;
    private final LoginPage loginPage;
    private final HomePage homePage;

    public LoginValidacao(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(this.driver);
        homePage = new HomePage(this.driver);
    }

    public void validacaoUsuarioLogado(String nome) {
        String texto = homePage.getNomeText().getText();
        TakeScreenshot.screenshotPage(driver, "Login - " + nome);
        Assertions.assertTrue(texto.contains(nome),
                "Usuario logado incorreto. Esperado conter '" + nome + "' mas veio: '" + texto + "'");
    }

    public void validacaoTelaLoginExibida() {
        boolean exibida = loginPage.getAcessarButton().isDisplayed();
        TakeScreenshot.screenshotPage(driver, "Logout - tela de login");
        Assertions.assertTrue(exibida, "A tela de login nao foi exibida apos o logout");
    }
}
