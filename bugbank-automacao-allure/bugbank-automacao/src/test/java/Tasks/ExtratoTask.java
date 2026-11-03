package Tasks;

import Dados.Conta;
import Framework.Utils.JsUtils;
import PageObjects.ExtratoPage;
import PageObjects.HomePage;
import Validacao.ExtratoValidacao;
import org.openqa.selenium.WebDriver;

public class ExtratoTask {

    private final WebDriver driver;
    private final HomePage homePage;
    private final ExtratoPage extratoPage;
    private final ExtratoValidacao extratoValidacao;

    public ExtratoTask(WebDriver driver) {
        this.driver = driver;
        homePage = new HomePage(this.driver);
        extratoPage = new ExtratoPage(this.driver);
        extratoValidacao = new ExtratoValidacao(this.driver);
    }


    public void validarExtrato(Conta conta, String descricao, double valorTransferencia) {
        JsUtils.clicarSeguro(driver, homePage.getExtratoButton());
        extratoValidacao.validacaoSaldoNoExtrato(conta);
        extratoValidacao.validacaoTransacaoNoExtrato(descricao, valorTransferencia);
        JsUtils.clicarSeguro(driver, extratoPage.getVoltarButton());
    }
}
