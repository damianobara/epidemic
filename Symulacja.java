import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

//Główna klasa programu.
public class Symulacja {

    public static void main(String[] args) {

        Properties propertiesEpidemii = Input.readInput();
        Dane daneEpidemii = new Dane();
        daneEpidemii.wczytajDane(propertiesEpidemii);

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(propertiesEpidemii.getProperty("plikZRaportem"), "UTF-8");
        } catch (IOException e) {
            System.out.println("Błąd plikuZRaportem");
            System.exit(1);
        }

        daneEpidemii.wypiszDane(writer);

        Random generator = new Random(daneEpidemii.seed());


        Model modelSpołeczeństwa = new Model(writer, generator, daneEpidemii.liczbaAgentów(),
                daneEpidemii.prawdTowarzyski(), daneEpidemii.liczbaDni(), daneEpidemii.śrZnajomych());

        Spoleczenstwo Spoleczenstwo = modelSpołeczeństwa.twórzSpoleczenstwo();
        Spoleczenstwo.wypiszGraf(writer);
        Epidemia epidemia = new Epidemia(writer, generator, daneEpidemii, Spoleczenstwo);
        epidemia.symulujEpidemię();
        writer.close();
    }
}
