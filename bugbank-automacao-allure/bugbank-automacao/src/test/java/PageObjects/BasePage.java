package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Base dos Page Objects. O BugBank e uma aplicacao React (os elementos
 * aparecem de forma assincrona), por isso todo acesso espera o elemento.
 */
public abstract class BasePage {

    protected final WebDriver driver;
    private final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebElement visivel(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected WebElement clicavel(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    protected WebElement presente(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected List<WebElement> todos(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return driver.findElements(by);
    }
}
