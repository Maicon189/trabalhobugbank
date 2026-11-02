package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getEmailInput() {
        return visivel(By.cssSelector(".card__login input[name='email']"));
    }

    public WebElement getPasswordInput() {
        return visivel(By.cssSelector(".card__login input[name='password']"));
    }

    public WebElement getAcessarButton() {
        return clicavel(By.xpath("//div[contains(@class,'card__login')]//button[normalize-space()='Acessar']"));
    }

    public WebElement getRegistrarButton() {
        return clicavel(By.xpath("//div[contains(@class,'card__login')]//button[normalize-space()='Registrar']"));
    }
}
