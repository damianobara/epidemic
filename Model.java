import java.io.PrintWriter;
import java.util.Random;


//Klasa służąca do tworzenia modelu symulującego społeczeństwo.
public class Model {
    private PrintWriter writer;
    private Random generator;
    private int liczbaAgentów;
    private float prawdTowarzyski;
    private int liczbaDni;
    private float śrZnajomych;

    public Model(PrintWriter writer, Random generator, int liczbaAgentów, float prawdTowarzyski, int liczbaDni, float śrZnajomych) {
        this.writer = writer;
        this.generator = generator;
        this.liczbaAgentów = liczbaAgentów;
        this.prawdTowarzyski = prawdTowarzyski;
        this.liczbaDni = liczbaDni;
        this.śrZnajomych = śrZnajomych;
    }

    //Metoda tworząca agentów zwykłych i towarzyskich według podanego prawdopodobieństwa.
    private Agent[] twórzAgentów() {
        Agent[] agenci = new Agent[liczbaAgentów];
        Agent nowy;
        boolean towarzyski;
        int agentChory = generator.nextInt(liczbaAgentów);
        for (int i = 0; i < liczbaAgentów; i++) {
            towarzyski = losujCzyTowarzyski();
            if (towarzyski) {
                nowy = new AgentTowarzyski(i, liczbaDni);
                if (i == agentChory) {
                    writer.println(i + 1 + "* towarzyski");
                    nowy.stan("chory");
                }
                else writer.println((i + 1 + " towarzyski"));
                nowy.poznajZnajomychZnajomych();
                agenci[i] = nowy;
            } else {
                nowy = new AgentZwykly(i, liczbaDni);
                if (i == agentChory) {
                    nowy.stan("chory");
                    writer.println(i + 1 + "* zwykły");
                }
                else writer.println((i + 1 + " zwykły"));
                agenci[i] = nowy;
            }
        }
        writer.println();
        return agenci;
    }

    //Metoda tworząca znajomości między agentami według podanego prawdopodobieństwa.
    private Agent[] twórzZnajomości(Agent[] agenci) {
        int i = 1;
        while (i <= liczbaAgentów * śrZnajomych / 2) {
            if (twórzLosowąZnajomość(agenci)) i++;
        }
        for (Agent agent : agenci) {
            agent.poznajZnajomychZnajomych();
        }
        return agenci;
    }


    private boolean twórzLosowąZnajomość(Agent[] agenci) {
        Agent agent1 = agenci[losujAgenta()];
        Agent agent2 = agenci[losujAgenta()];
        if (!agent1.dodajZnajomego(agent2)) return false;
        agent2.dodajZnajomego(agent1);
        return true;
    }

    private boolean losujCzyTowarzyski() {
        return (generator.nextDouble() <= prawdTowarzyski);
    }

    private int losujAgenta() {
        return generator.nextInt(liczbaAgentów);
    }

    public Spoleczenstwo twórzSpoleczenstwo() {
        return new Spoleczenstwo(twórzZnajomości(twórzAgentów()));
    }
}
