package Dados;

import java.util.Locale;

public class Moeda {


    public static String formatar(double valor) {
        return String.format(Locale.of("pt", "BR"), "%,.2f", valor);
    }
}
