package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getNomeText() {
        return visivel(By.id("textName"));
    }

    public WebElement getSaldoText() {
        return visivel(By.id("textBalance"));
    }

    public WebElement getTransferenciaButton() {
        return clicavel(By.id("btn-TRANSFERÊNCIA"));
    }

    public WebElement getExtratoButton() {
        return clicavel(By.id("btn-EXTRATO"));
    }

    public WebElement getSairButton() {
        return clicavel(By.id("btnExit"));
    }
}
