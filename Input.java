import java.io.*;
import java.nio.charset.Charset;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

//Klasa służąca do wczytywania inputu do klasy Properties.
public class Input {

    static public Properties readInput() {

        Properties prop = new Properties();
        FileInputStream input = null;

        FileInputStream inputXML;
        try {
            input = new FileInputStream(new File("defaults.properties"));
            prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku default.properties");
            System.exit(1);
        } catch (IOException e) {
            System.exit(1);
        }

        try {
            inputXML = new FileInputStream(new File("simulation-conf.xml"));
            prop.loadFromXML(inputXML);
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku simulation-conf.xml");
            System.exit(1);
        } catch (InvalidPropertiesFormatException e) {
            System.out.println("simulation-conf.xml nie jest XML");
            System.exit(1);
        } catch (IOException e) {
            System.exit(1);
        }

        if (prop.getProperty("plikZRaportem") == null) {
            System.out.println("Brak wartości dla klucza plikZRaportem");
            System.exit(1);
        }

        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;

    }
}
