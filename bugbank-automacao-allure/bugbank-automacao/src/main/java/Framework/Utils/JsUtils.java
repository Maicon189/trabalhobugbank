package Framework.Utils;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Utilitarios para situacoes em que o comportamento padrao do Selenium nao basta.
 */
public class JsUtils {

    /** Clique via JavaScript (ex.: checkbox customizado escondido atras de um "slider"). */
    public static void clicar(WebDriver driver, WebElement elemento) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", elemento);
    }

    /** Rola ate o elemento e clica; se algo estiver por cima, clica via JavaScript. */
    public static void clicarSeguro(WebDriver driver, WebElement elemento) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", elemento);
        try {
            elemento.click();
        } catch (ElementClickInterceptedException e) {
            clicar(driver, elemento);
        }
    }

    /** Seleciona o texto que ja existe no campo e substitui pelo novo. */
    public static void digitar(WebElement elemento, String texto) {
        elemento.sendKeys(Keys.chord(Keys.CONTROL, "a"), texto);
    }
}
