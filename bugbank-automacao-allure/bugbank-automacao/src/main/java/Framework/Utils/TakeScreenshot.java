package Framework.Utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class TakeScreenshot {

    /** Tira um print da tela e anexa ao relatorio do Allure. */
    public static void screenshotPage(WebDriver webDriver, String name) {

        byte[] screenshotBytes = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(name, "image/png", new ByteArrayInputStream(screenshotBytes), ".png");
    }
}
