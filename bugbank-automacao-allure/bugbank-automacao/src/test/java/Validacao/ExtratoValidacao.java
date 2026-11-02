package Validacao;

import Dados.Conta;
import Dados.Moeda;
import Framework.Utils.TakeScreenshot;
import PageObjects.ExtratoPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ExtratoValidacao {

    private final WebDriver driver;
    private final ExtratoPage extratoPage;

    public ExtratoValidacao(WebDriver driver) {
        this.driver = driver;
        extratoPage = new ExtratoPage(this.driver);
    }

    public void validacaoSaldoNoExtrato(Conta conta) {
        String esperado = Moeda.formatar(conta.getSaldo());
        String texto = extratoPage.getSaldoText().getText();
        TakeScreenshot.screenshotPage(driver, "Extrato - saldo de " + conta.getNome());
        Assertions.assertTrue(texto.contains(esperado),
                "Saldo no extrato incorreto. Esperado conter '" + esperado + "' mas veio: '" + texto + "'");
    }

    public void validacaoTransacaoNoExtrato(String descricao, double valor) {
        List<WebElement> descricoes = extratoPage.getDescricoes();
        List<WebElement> valores = extratoPage.getValores();
        TakeScreenshot.screenshotPage(driver, "Extrato - transacao '" + descricao + "'");

        int posicao = -1;
        for (int i = 0; i < descricoes.size(); i++) {
            if (descricoes.get(i).getText().trim().equals(descricao)) {
                posicao = i;
                break;
            }
        }

        Assertions.assertTrue(posicao >= 0,
                "Transacao com a descricao '" + descricao + "' nao foi encontrada no extrato");

        String esperado = Moeda.formatar(valor);
        String textoValor = valores.get(posicao).getText();
        Assertions.assertTrue(textoValor.contains(esperado),
                "Valor da transacao incorreto. Esperado conter '" + esperado + "' mas veio: '" + textoValor + "'");
    }
}
