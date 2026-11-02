package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CadastroPage extends BasePage {

    public CadastroPage(WebDriver driver) {
        super(driver);
    }

    /** Verifica (sem esperar) se o formulario de cadastro ja esta na tela. */
    public boolean isFormularioVisivel() {
        var campos = driver.findElements(By.cssSelector(".card__register input[name='email']"));
        return !campos.isEmpty() && campos.get(0).isDisplayed();
    }

    public WebElement getEmailInput() {
        return visivel(By.cssSelector(".card__register input[name='email']"));
    }

    public WebElement getNomeInput() {
        return visivel(By.cssSelector(".card__register input[name='name']"));
    }

    public WebElement getPasswordInput() {
        return visivel(By.cssSelector(".card__register input[name='password']"));
    }

    public WebElement getPasswordConfirmationInput() {
        return visivel(By.cssSelector(".card__register input[name='passwordConfirmation']"));
    }

    public WebElement getCriarContaComSaldoToggle() {
        return presente(By.id("toggleAddBalance"));
    }

    public WebElement getCadastrarButton() {
        return clicavel(By.xpath("//div[contains(@class,'card__register')]//button[normalize-space()='Cadastrar']"));
    }

    public WebElement getVoltarLoginLink() {
        return clicavel(By.xpath("//*[self::a or self::button][normalize-space()='Voltar ao login']"));
    }
}
