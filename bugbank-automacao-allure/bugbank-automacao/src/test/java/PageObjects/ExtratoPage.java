package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ExtratoPage extends BasePage {

    public ExtratoPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getSaldoText() {
        return visivel(By.id("textBalanceAvailable"));
    }

    public List<WebElement> getDescricoes() {
        return todos(By.id("textDescription"));
    }

    public List<WebElement> getValores() {
        return todos(By.id("textTransferValue"));
    }

    public WebElement getVoltarButton() {
        return clicavel(By.id("btnBack"));
    }
}
