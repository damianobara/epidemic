import java.io.PrintWriter;

//Klasa przechowująca aktalne informacje o populacji i stanie zdrowia Agentów oraz podawania ich codziennie.
public class BBC {
    private int populacja;
    public int zdrowi;
    public int chorzy;
    public int uodpornieni;

    public BBC (int populacja){
        this.populacja = populacja;
        this.zdrowi = populacja - 1;
        this.chorzy = 1;
        this.uodpornieni = 0;
    }

    public void podajWiadomości(PrintWriter writer){
        writer.println(zdrowi + " " + chorzy + " " + uodpornieni + " ");
    }

    public void zdrowi(int zdrowi) {
        this.zdrowi = zdrowi;
    }

    public void chorzy(int chorzy) {
        this.chorzy = chorzy;
    }

    public void uodpornieni(int uodpornieni) {
        this.uodpornieni = uodpornieni;
    }

    public int zdrowi() {
        return zdrowi;
    }

    public int chorzy() {
        return chorzy;
    }

    public int uodpornieni() {
        return uodpornieni;
    }
}
