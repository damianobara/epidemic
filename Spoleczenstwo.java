import java.io.PrintWriter;

//Klasa reprezentująca sieć Agentów.
public class Spoleczenstwo {
    private Agent[] agenci;

    public Spoleczenstwo(Agent[] agenci){
        this.agenci = agenci;
    }

    public Agent[] agenci() {
        return agenci;
    }

    public void wypiszGraf(PrintWriter writer){
        for (Agent agent : agenci) {
            writer.print(agent.id() + " ");
            for (Agent znajomy: agent.znajomi()) {
                writer.print(znajomy.id() + " ");
            }
            writer.println();
        }
        writer.println();
    }
}

