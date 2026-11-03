package Dados;

import Framework.Utils.FileOperations;

import java.util.Properties;

public class Conta {

    private static final double SALDO_INICIAL_BUGBANK = 1000.00;

    private final String email;
    private final String nome;
    private final String senha;
    private final boolean comSaldo;

    private String numero;
    private String digito;
    private double saldo;

    public Conta(String email, String nome, String senha, boolean comSaldo) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.comSaldo = comSaldo;
        this.saldo = comSaldo ? SALDO_INICIAL_BUGBANK : 0.0;
    }


    public static Conta origem() {
        return doProperties("origem");
    }

    public static Conta destino() {
        return doProperties("destino");
    }

    private static Conta doProperties(String prefixo) {
        Properties dados = FileOperations.getProperties("contas");
        return new Conta(
                dados.getProperty(prefixo + ".email"),
                dados.getProperty(prefixo + ".nome"),
                dados.getProperty(prefixo + ".senha"),
                true);
    }

    public String getEmail() { return email; }
    public String getNome() { return nome; }
    public String getSenha() { return senha; }
    public boolean isComSaldo() { return comSaldo; }
    public String getNumero() { return numero; }
    public String getDigito() { return digito; }
    public double getSaldo() { return saldo; }

    public void setNumeroConta(String numero, String digito) {
        this.numero = numero;
        this.digito = digito;
    }

    public void debitar(double valor) { this.saldo -= valor; }
    public void creditar(double valor) { this.saldo += valor; }
}
