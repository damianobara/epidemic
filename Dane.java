import java.io.PrintWriter;
import java.util.Properties;

//Klasa służąca do wczytywania danych z pliku properties do właściwego formatu oraz wypisywania ich.
//Sprawdza czy podane dane są poprawne.
public class Dane {
    public static final int minLiczbaAgentów = 1;
    public static final int maxLiczbaAgentów = 1000000;
    public static final float minPrawd = 0;
    public static final float maxPrawd = 1;
    public static final int minLiczbaDni = 0;
    public static final int maxLiczbaDni = 1000;
    public static final int minŚrZnajomych = 0;

    private long seed;
    private int liczbaAgentów;
    private float prawdTowarzyski;
    private float prawdSpotkania;
    private float prawdZarażenia;
    private float prawdWyzdrowienia;
    private float śmiertelność;
    private int liczbaDni;
    private float śrZnajomych;
    private String plikZRaportem;

    public Dane() {
    }

    private void wczytajSeed(String seedString) {
        try {
            seed = Long.parseLong(seedString);
        } catch (NumberFormatException e) {
            System.out.println("Niedozwolona wartość " + seedString + " dla klucza seed");
            System.exit(1);
        }
    }

    private void wczytajLiczbaAgentów(String liczbaAgentówString) {
        try {
            liczbaAgentów = Integer.parseInt(liczbaAgentówString);
        } catch (NumberFormatException e) {
            System.out.println("Niedozwolona wartość " + liczbaAgentówString + " dla klucza liczbaAgentóœ");
            System.exit(1);
        }
        if (liczbaAgentów < minLiczbaAgentów || maxLiczbaAgentów < liczbaAgentów) {
            System.out.println("Niedozwolona wartość " + liczbaAgentówString + " dla klucza liczbaAgentóœ");
            System.exit(1);
        }
    }

    private void wczytajPrawdTowarzyski(String prawdTowarzyskiString) {
        try {
            prawdTowarzyski = Float.parseFloat(prawdTowarzyskiString);
        } catch (NumberFormatException e) {
            System.out.println("Niedozwolona wartość " + prawdTowarzyskiString + " dla klucza PrawdTowarzyski");
            System.exit(1);
        }
        if (prawdTowarzyski < minPrawd || maxPrawd < prawdTowarzyski) {
            System.out.println("Niedozwolona wartość " + prawdTowarzyskiString + " dla klucza PrawdTowarzyski");
            System.exit(1);

        }
    }

    private void wczytajPrawdSpotkania(String prawdSpotkaniaString) {
        try {
            prawdSpotkania = Float.parseFloat(prawdSpotkaniaString);
        } catch (NumberFormatException e) {
            System.out.println("Niedozwolona wartość " + prawdSpotkaniaString + " dla klucza prawdSpotkania");
            System.exit(1);
        }
        if (prawdSpotkania < minPrawd || maxPrawd < prawdSpotkania) {
            System.out.println("Niedozwolona wartość " + prawdSpotkaniaString + " dla klucza prawdSpotkania");
            System.exit(1);
        }
    }


    private void wczytajPrawdZarażenia(String prawdZarażeniaString) {
        try {
            prawdZarażenia = Float.parseFloat(prawdZarażeniaString);
        } catch (NumberFormatException e) {
            System.out.println("Niedozwolona wartość " + prawdZarażenia + " dla klucza prawdZarażenia");
            System.exit(1);
        }
        if (prawdZarażenia < minPrawd || maxPrawd < prawdZarażenia) {
            System.out.println("Niedozwolona wartość " + prawdZarażeniaString + " dla klucza prawdZarażenia");
            System.exit(1);
        }
    }

    private void wczytajPrawdWyzdrowienia(String prawdWyzdrowieniaString) {
        try {
            prawdWyzdrowienia = Float.parseFloat(prawdWyzdrowieniaString);
        } catch (NumberFormatException e) {
            System.out.println("Niedozwolona wartość " + prawdWyzdrowieniaString + " dla klucza prawdWyzdrowienia");
            System.exit(1);
        }
        if (prawdWyzdrowienia < minPrawd || maxPrawd < prawdWyzdrowienia) {
            System.out.println("Niedozwolona wartość " + prawdWyzdrowieniaString + " dla klucza prawdWyzdrowienia");
            System.exit(1);
        }
    }

    private void wczytajŚmiertelność(String śmiertelnośćString) {
        try {
            śmiertelność = Float.parseFloat(śmiertelnośćString);
        } catch (NumberFormatException e) {
            System.out.println("Niedozwolona wartość " + śmiertelnośćString + " dla klucza śmiertelność");
            System.exit(1);
        }
        if (śmiertelność < minPrawd || maxPrawd < śmiertelność) {
            System.out.println("Niedozwolona wartość " + śmiertelnośćString + " dla klucza śmiertelność");
            System.exit(1);
        }
    }

    private void wczytajLiczbaDni(String liczbaDniString) {
        try {
            liczbaDni = Integer.parseInt(liczbaDniString);
        } catch (NumberFormatException e) {
            System.out.println("Niedozwolona wartość " + liczbaDniString + " dla klucza liczbaDni");
            System.exit(1);
        }
        if (liczbaDni < minLiczbaDni || maxLiczbaDni < liczbaDni) {
            System.out.println("Niedozwolona wartość " + liczbaDniString + " dla klucza liczbaDni");
            System.exit(1);
        }
    }

    private void wczytajŚrZnajomych(String śrZnajomychString) {
        try {
            śrZnajomych = Float.parseFloat(śrZnajomychString);
        } catch (NumberFormatException e) {
            System.out.println("Niedozwolona wartość " + śrZnajomychString + " dla klucza śrZnajomych");
            System.exit(1);
        }
        if (śrZnajomych < minŚrZnajomych || liczbaAgentów - 1 < śrZnajomych) {
            System.out.println("Niedozwolona wartość " + śrZnajomychString + " dla klucza śrZnajomych");
            System.exit(1);
        }
    }

    private void wczytajPlikZRaportem(String plikZRaportemString) {
        plikZRaportem = plikZRaportemString;
    }


    public void wczytajDane(Properties properties) {
        wczytajSeed(properties.getProperty("seed"));
        wczytajLiczbaAgentów(properties.getProperty("liczbaAgentów"));
        wczytajPrawdTowarzyski(properties.getProperty("prawdTowarzyski"));
        wczytajPrawdSpotkania(properties.getProperty("prawdSpotkania"));
        wczytajPrawdZarażenia(properties.getProperty("prawdZarażenia"));
        wczytajPrawdWyzdrowienia(properties.getProperty("prawdWyzdrowienia"));
        wczytajŚmiertelność(properties.getProperty("śmiertelność"));
        wczytajLiczbaDni(properties.getProperty("liczbaDni"));
        wczytajŚrZnajomych(properties.getProperty("śrZnajomych"));
        wczytajPlikZRaportem(properties.getProperty("plikZRaportem"));
    }

    public long seed() {
        return seed;
    }

    public int liczbaAgentów() {
        return liczbaAgentów;
    }

    public float prawdTowarzyski() {
        return prawdTowarzyski;
    }

    public float prawdSpotkania() {
        return prawdSpotkania;
    }

    public float prawdZarażenia() {
        return prawdZarażenia;
    }

    public float prawdWyzdrowienia() {
        return prawdWyzdrowienia;
    }

    public float śmiertelność() {
        return śmiertelność;
    }

    public int liczbaDni() {
        return liczbaDni;
    }

    public float śrZnajomych() {
        return śrZnajomych;
    }

    public String plikZRaportem() {
        return plikZRaportem;
    }

    public void wypiszDane(PrintWriter writer) {
        writer.println("seed= " + seed);
        writer.println("liczbaAgentów=" + liczbaAgentów);
        writer.println("prawdTowarzyski=" + prawdTowarzyski);
        writer.println("prawdSpotkania=" + prawdSpotkania);
        writer.println("prawdZarażenia=" + prawdZarażenia);
        writer.println("prawdWyzdrowienia=" + prawdWyzdrowienia);
        writer.println("śmiertelność=" + śmiertelność);
        writer.println("liczbaDni=" + liczbaDni);
        writer.println("śrZnajomych=" + śrZnajomych);
        writer.println();

    }
}
