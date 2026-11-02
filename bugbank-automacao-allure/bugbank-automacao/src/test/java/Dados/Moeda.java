package Dados;

import java.util.Locale;

public class Moeda {

    /** Formata como o BugBank exibe: 1.000,00 */
    public static String formatar(double valor) {
        return String.format(Locale.of("pt", "BR"), "%,.2f", valor);
    }
}
