package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TransferenciaPage extends BasePage {

    public TransferenciaPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getNumeroContaInput() {
        return visivel(By.name("accountNumber"));
    }

    public WebElement getDigitoInput() {
        return visivel(By.name("digit"));
    }

    public WebElement getValorInput() {
        return visivel(By.name("transferValue"));
    }

    public WebElement getDescricaoInput() {
        return visivel(By.name("description"));
    }

    public WebElement getTransferirAgoraButton() {
        return clicavel(By.xpath("//button[normalize-space()='Transferir agora']"));
    }

    public WebElement getVoltarButton() {
        return clicavel(By.id("btnBack"));
    }
}
