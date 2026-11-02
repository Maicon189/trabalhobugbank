package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/** Elementos compartilhados entre telas (modal de mensagem). */
public class GenericPage extends BasePage {

    public GenericPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getModalText() {
        return visivel(By.id("modalText"));
    }

    public WebElement getModalCloseButton() {
        return clicavel(By.id("btnCloseModal"));
    }
}
