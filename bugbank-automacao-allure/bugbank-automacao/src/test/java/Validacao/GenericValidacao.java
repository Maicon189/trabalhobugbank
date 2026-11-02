package Validacao;

import Framework.Utils.TakeScreenshot;
import PageObjects.GenericPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class GenericValidacao {

    private final WebDriver driver;
    private final GenericPage genericPage;

    public GenericValidacao(WebDriver driver) {
        this.driver = driver;
        genericPage = new GenericPage(this.driver);
    }

    public void validacaoModalContem(String trecho) {
        String texto = genericPage.getModalText().getText();
        TakeScreenshot.screenshotPage(driver, "Modal - " + texto);
        Assertions.assertTrue(texto.toLowerCase().contains(trecho.toLowerCase()),
                "Mensagem do modal incorreta. Esperado conter '" + trecho + "' mas veio: '" + texto + "'");
    }
}
