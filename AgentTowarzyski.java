import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

//Klasa reprezentująca pojedyńczego zwykłego Agenta.
public class AgentTowarzyski extends Agent {
    //Zbiór z którego Agent zdrowy będzie losował osoby do spotkania.
    public HashSet<Agent> znajomiIZnajomiZnajomych;

    public AgentTowarzyski(int id, int dni) {
        super(id, dni);
        znajomiIZnajomiZnajomych = new HashSet<>();
    }

    //Metoda umawiająca plany spotkań danego agenta na najbliższe dni.
    public void umówSpotkania(Random generator, float prawdSpotkania, int dzisiaj, int ilePozostało) {
        if (stan.equals("zdrowy") || stan.equals("chory")) {
            Agent agentNaSpotkanie;
            int dzieńSpotkania;
            ArrayList<Agent> help;

            while (generator.nextDouble() <= prawdSpotkania) {
                agentNaSpotkanie = losujAgentaNaSpotkanie(generator);
                dzieńSpotkania = generator.nextInt(dzisiaj + ilePozostało);

                help = plany.get(dzieńSpotkania);
                if (agentNaSpotkanie == null) {
                    System.exit(2);
                }
                help.add(agentNaSpotkanie);
                plany.set(dzieńSpotkania, help);

            }
        }
    }

    protected Agent losujAgentaNaSpotkanie(Random generator) {
        Agent znajomy = null;
        assert !stan.equals("martwy") : "DUPA";

        if (stan.equals("chory")) {
            int który = generator.nextInt(znajomi().size()) + 1;
            Iterator<Agent> iterator = znajomi().iterator();
            for (int i = 1; i <= który && iterator.hasNext(); i++) {
                znajomy = iterator.next();
                assert znajomy != null : "o co chodzi";
            }
        }
        else if (stan.equals("zdrowy")) {
            int który = generator.nextInt(znajomiIZnajomiZnajomych.size()) + 1;
            Iterator<Agent> iterator = znajomiIZnajomiZnajomych.iterator();
            for (int i = 1; i <= który; i++) {
                znajomy = iterator.next();
                assert znajomy != null : "o co chodzi";
            }
        }
        assert znajomy != null;
        return znajomy;
    }


    @Override
    //Metoda służąca do modyfikowania zbioru Agentów tak, by zawierał on wszystkich znajomych i znajommych znajomych danego agenta
    public void poznajZnajomychZnajomych() {
        znajomiIZnajomiZnajomych.addAll(znajomi);
        for (Agent znajomy : znajomi) {
            for (Agent znajomyZnajomego : znajomy.znajomi) {
                if (!znajomyZnajomego.equals(this)) znajomiIZnajomiZnajomych.add(znajomyZnajomego);
            }
        }
    }
}
