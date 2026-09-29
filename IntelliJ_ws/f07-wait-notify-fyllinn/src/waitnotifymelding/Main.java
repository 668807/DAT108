package waitnotifymelding;

public class Main {

    static void main() {

        Melding melding = new Melding();

        Thread printThread = new Thread( () -> IO.println(melding.getTekst()));
        Thread giVerdiTraad = new Thread( () -> melding.setTekst("Hallo"));

        printThread.start();
        giVerdiTraad.start();
    }
}
