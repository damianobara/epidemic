import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

//Klasa reprezentująca pojedyńczego zwykłego Agenta.
public class AgentZwykly extends Agent{

    public AgentZwykly(int id, int dni){
        super(id, dni);
    }

    //Metoda umawiająca plany spotkań danego agenta na najbliższe dni.
    public void umówSpotkania(Random generator, float prawdSpotkania, int dzisiaj, int ilePozostało){
        Agent agentNaSpotkanie;
        int dzieńSpotkania;
        ArrayList<Agent> help;
        if (stan().equals("zdrowy") || stan().equals("uodporniony")) {
            while (generator.nextDouble() <= prawdSpotkania){
                agentNaSpotkanie = losujAgentaNaSpotkanie(generator);
                dzieńSpotkania = generator.nextInt(dzisiaj + ilePozostało);

                help = plany.get(dzieńSpotkania);
                help.add(agentNaSpotkanie);
                plany.set(dzieńSpotkania, help);

            }
        }
        else if (stan().equals("chory")){
            while (generator.nextDouble() <= prawdSpotkania/2){
                agentNaSpotkanie = losujAgentaNaSpotkanie(generator);
                dzieńSpotkania = generator.nextInt(dzisiaj + ilePozostało);

                help = plany.get(dzieńSpotkania);
                help.add(agentNaSpotkanie);
                plany.set(dzieńSpotkania,help);
            }
        }
    }

    protected Agent losujAgentaNaSpotkanie(Random generator){
        int który = generator.nextInt(znajomi().size()) + 1;
        Iterator<Agent> iterator = znajomi().iterator();
        Agent znajomy = null;
        for (int i = 1; i <= który && iterator.hasNext(); i++) {
            znajomy = iterator.next();
        }
        assert (znajomy != null);
        return znajomy;
    }

    @Override
    //Metoda pusta, ponieważ agenta zwykłego nie obchodzą znajomi znajomych.
    public void poznajZnajomychZnajomych(){
    }
}
