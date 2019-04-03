import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

//Głowna klasa reprezentująca epidemię, służąca do przeprowadzania jej symulacji.
public class Epidemia {

    private PrintWriter writer;
    private Random generator;
    private int liczbaAgentów;
    private float prawdTowarzyski;
    private float prawdSpotkania;
    private float prawdZarażenia;
    private float prawdWyzdrowienia;
    private float śmiertelność;
    private int liczbaDni;
    private float śrZnajomych;
    private String plikZRaportem;

    private int dzieńEpidemii;
    private Spoleczenstwo Spoleczenstwo;
    private BBC bbc;

    public Epidemia(PrintWriter writer, Random generator, Dane dane, Spoleczenstwo Spoleczenstwo) {
        this.writer = writer;
        this.generator = generator;
        liczbaAgentów = dane.liczbaAgentów();
        prawdTowarzyski = dane.prawdTowarzyski();
        prawdSpotkania = dane.prawdSpotkania();
        prawdZarażenia = dane.prawdZarażenia();
        prawdWyzdrowienia = dane.prawdWyzdrowienia();
        śmiertelność = dane.śmiertelność();
        liczbaDni = dane.liczbaDni();
        śrZnajomych = dane.śrZnajomych();
        plikZRaportem = dane.plikZRaportem();

        dzieńEpidemii = 0;
        this.Spoleczenstwo = Spoleczenstwo;
        bbc = new BBC(liczbaAgentów);
    }

    //Metoda symulująca poranne umieranie agentów.
    private void przeprowadźUmieranie() {
        for (int i = 0; i < liczbaAgentów; i++) {
            if (Spoleczenstwo.agenci()[i].stan().equals("chory")) {
                if (generator.nextDouble() < śmiertelność) {
                    bbc.chorzy(bbc.chorzy - 1);
                    Spoleczenstwo.agenci()[i].stan("martwy");
                }
            }
        }
    }

    //Metoda symulująca poranne zdrowienie pacjentów.
    private void przeprowadźOzdrawianie() {
        for (int i = 0; i < liczbaAgentów; i++) {
            if (Spoleczenstwo.agenci()[i].stan().equals("chory")) {
                if (generator.nextDouble() < prawdWyzdrowienia) {
                    bbc.chorzy(bbc.chorzy - 1);
                    bbc.uodpornieni(bbc.uodpornieni + 1);
                    Spoleczenstwo.agenci()[i].stan("uodporniony");
                }
            }
        }
    }

    //Metoda przeprowadzająca wszystkie zaplanowane spotkania danego dnia.
    private void przeprowadźSpotykanie() {
        for (Agent agent : Spoleczenstwo.agenci()) {
            ArrayList<Agent> znajomiNaDziś = agent.plany.get(dzieńEpidemii);
            for (Agent kolega : znajomiNaDziś) {
                if (agent == null || kolega == null) {
                    System.exit(2);
                }
                if (agent.stan.equals("chory") && kolega.stan.equals("zdrowy")) {
                    if (generator.nextDouble() <= prawdZarażenia) {
                        kolega.stan("chory");
                        bbc.zdrowi(bbc.zdrowi() - 1);
                        bbc.chorzy(bbc.chorzy() + 1);
                    }
                } else if (agent.stan().equals("zdrowy") && kolega.stan.equals("chory")) {
                    if (generator.nextDouble() <= prawdZarażenia) {
                        agent.stan("chory");
                        bbc.zdrowi(bbc.zdrowi - 1);
                        bbc.chorzy(bbc.chorzy() - 1);
                    }
                }
            }
        }
    }

    //Metoda symulująca planowanie spotkań danego dnia.
    private void przeprowadźUmawianie() {
        for (int i = 0; i < liczbaAgentów; i++) {
            Spoleczenstwo.agenci()[i].umówSpotkania(generator, prawdSpotkania, dzieńEpidemii, liczbaDni - dzieńEpidemii);
        }
    }

    //Metoda symulująca 1 dzień epidemii.
    public void dzieńEpidemii() {
        przeprowadźUmieranie();
        przeprowadźOzdrawianie();
        przeprowadźUmawianie();
        przeprowadźSpotykanie();
        dzieńEpidemii++;

    }

    //Metoda symulująca epidemię.
    public void symulujEpidemię() {
        while (dzieńEpidemii < liczbaDni) {
            dzieńEpidemii();
            bbc.podajWiadomości(writer);
        }
    }

}
