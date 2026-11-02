package Framework.Utils;

import net.datafaker.Faker;

import java.text.Normalizer;
import java.util.Locale;

/** Gera dados aleatorios (nome, e-mail, CEP) para uso nos testes. */
public class FakersGeneration {

    private final Faker faker;

    public FakersGeneration() {
        faker = new Faker(Locale.forLanguageTag("pt-BR"));
    }

    public static String removerAcentos(String texto) {
        return Normalizer
                .normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
    }

    public String getFirstName() {
        return removerAcentos(faker.name().firstName());
    }

    public String getLastName() {
        return removerAcentos(faker.name().lastName());
    }

    public String getZipCode() {
        return faker.address().zipCode();
    }

    public String getEmail() {
        return faker.internet().emailAddress();
    }
}
