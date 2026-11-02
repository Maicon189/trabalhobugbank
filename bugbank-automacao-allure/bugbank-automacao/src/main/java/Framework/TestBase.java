package Framework;

import Framework.Browser.DriverManager;
import Framework.Browser.TypeBrowser;
import Framework.Utils.FileOperations;
import Framework.Utils.TakeScreenshot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.WebDriver;

public class TestBase extends DriverManager {

    private static final String URL = FileOperations.getProperties("url").getProperty("index");

    /** Se o teste falhar, anexa um print da tela ao relatorio do Allure antes de fechar o navegador. */
    @RegisterExtension
    public final TestExecutionExceptionHandler screenshotNaFalha = (context, erro) -> {
        try {
            TakeScreenshot.screenshotPage(getDriverManager(), "ERRO");
        } catch (Exception ignorado) {
            // se nao for possivel tirar o print, segue com o erro original
        }
        throw erro;
    };

    public static WebDriver getDriverManager() {
        return getDriver(TypeBrowser.CHROME);
    }

    @BeforeEach
    public void setUp() {
        Assertions.assertNotNull(URL, "URL nao encontrada em url.properties (chave 'index')");
        getDriverManager().get(URL);
    }

    @AfterEach
    public void finish() {
        fecharDriver();
    }
}
