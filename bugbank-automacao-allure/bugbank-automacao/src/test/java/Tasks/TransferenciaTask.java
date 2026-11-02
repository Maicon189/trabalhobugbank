package Tasks;

import Dados.Conta;
import Framework.Utils.JsUtils;
import PageObjects.GenericPage;
import PageObjects.HomePage;
import PageObjects.TransferenciaPage;
import Validacao.GenericValidacao;
import Validacao.HomeValidacao;
import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;

public class TransferenciaTask {

    private final WebDriver driver;
    private final HomePage homePage;
    private final TransferenciaPage transferenciaPage;
    private final GenericPage genericPage;
    private final GenericValidacao genericValidacao;
    private final HomeValidacao homeValidacao;

    public TransferenciaTask(WebDriver driver) {
        this.driver = driver;
        homePage = new HomePage(this.driver);
        transferenciaPage = new TransferenciaPage(this.driver);
        genericPage = new GenericPage(this.driver);
        genericValidacao = new GenericValidacao(this.driver);
        homeValidacao = new HomeValidacao(this.driver);
    }

    public void realizarTransferencia(Conta origem, Conta destino, double valor, String descricao) {

        homeValidacao.validacaoSaldoNaHome(origem);
        JsUtils.clicarSeguro(driver, homePage.getTransferenciaButton());

        JsUtils.digitar(transferenciaPage.getNumeroContaInput(), destino.getNumero());
        JsUtils.digitar(transferenciaPage.getDigitoInput(), destino.getDigito());
        JsUtils.digitar(transferenciaPage.getValorInput(),
                BigDecimal.valueOf(valor).stripTrailingZeros().toPlainString());
        JsUtils.digitar(transferenciaPage.getDescricaoInput(), descricao);
        JsUtils.clicarSeguro(driver, transferenciaPage.getTransferirAgoraButton());

        genericValidacao.validacaoModalContem("sucesso");
        JsUtils.clicarSeguro(driver, genericPage.getModalCloseButton());

        origem.debitar(valor);
        destino.creditar(valor);

        JsUtils.clicarSeguro(driver, transferenciaPage.getVoltarButton());
        homeValidacao.validacaoSaldoNaHome(origem);
    }
}
