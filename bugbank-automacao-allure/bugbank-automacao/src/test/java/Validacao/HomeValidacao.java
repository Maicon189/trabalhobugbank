package Validacao;

import Dados.Conta;
import Dados.Moeda;
import Framework.Utils.TakeScreenshot;
import PageObjects.HomePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class HomeValidacao {

    private final WebDriver driver;
    private final HomePage homePage;

    public HomeValidacao(WebDriver driver) {
        this.driver = driver;
        homePage = new HomePage(this.driver);
    }

    public void validacaoSaldoNaHome(Conta conta) {
        String esperado = Moeda.formatar(conta.getSaldo());
        String texto = homePage.getSaldoText().getText();
        TakeScreenshot.screenshotPage(driver, "Home - saldo de " + conta.getNome());
        Assertions.assertTrue(texto.contains(esperado),
                "Saldo na home incorreto. Esperado conter '" + esperado + "' mas veio: '" + texto + "'");
    }
}
