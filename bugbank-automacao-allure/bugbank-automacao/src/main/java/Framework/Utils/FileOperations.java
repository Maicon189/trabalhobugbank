package Framework.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class FileOperations {

    private static final String PASTA = "Properties";

    private static final String DIR_PATH_PROPERTIES =
            System.getProperty("user.dir") + File.separator + "src" + File.separator +
                    "main" + File.separator + "resources" + File.separator +
                    PASTA + File.separator;

    /** Le um arquivo .properties: primeiro pela pasta do projeto e, se nao achar, pelo classpath. */
    public static Properties getProperties(String fileName) {

        Properties prop = new Properties();
        String caminho = DIR_PATH_PROPERTIES + fileName + ".properties";

        try (InputStream input = new FileInputStream(new File(caminho))) {
            prop.load(input);
            return prop;
        } catch (Exception e) {
            // segue para o classpath
        }

        try (InputStream input = FileOperations.class.getClassLoader()
                .getResourceAsStream(PASTA + "/" + fileName + ".properties")) {
            if (input != null) {
                prop.load(input);
                return prop;
            }
        } catch (IOException e) {
            // cai na mensagem abaixo
        }

        System.out.println("Nao foi possivel carregar o arquivo de propriedade: " + caminho);
        return prop;
    }

    /** Salva (ou atualiza) uma chave em um arquivo .properties. */
    public static void setProperties(String fileName, String propKey, String propValue) {

        Properties prop = getProperties(fileName);

        try (OutputStream output = new FileOutputStream(DIR_PATH_PROPERTIES + fileName + ".properties")) {
            prop.setProperty(propKey, propValue);
            prop.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
