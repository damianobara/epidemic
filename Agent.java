import java.util.*;

//Abstrakcyjna klasa reprezentująca pojedyńczego Agenta
public abstract class Agent {
    protected int id;
    protected String stan;
    protected HashSet<Agent> znajomi;
    protected ArrayList<ArrayList<Agent>> plany;

    public Agent(int id, int dni) {
        this.id = id;
        this.stan = "zdrowy";
        znajomi = new HashSet<>();
        plany = new ArrayList<>(dni);
        for (int i = 1; i <= dni; i++) plany.add(new ArrayList<>());
    }

    public int id() {
        return id;
    }

    public HashSet<Agent> znajomi() {
        return znajomi;
    }

    public boolean dodajZnajomego(Agent agent) {
        if (agent.equals(this)) return false;
        if (znajomi.contains(agent)) return false;
        znajomi.add(agent);
        return true;
    }

    public String stan() {
        return stan;
    }

    public void stan(String stan) {
        this.stan = stan;
    }

    @Override
    //Przedefiniowana metoda equals zwracająca równość 2 agentów, jeśli mają takie samo id.
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return id == agent.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public abstract void umówSpotkania(Random generator, float prawdSpotkania, int dzisiaj, int ilePozostało);

    protected abstract Agent losujAgentaNaSpotkanie(Random generator);

    public abstract void poznajZnajomychZnajomych();


}
