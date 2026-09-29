package no.hvl.dat108.oblig2.oppg1;

public class Main {

    public static void main(String[] args) {
        Melding melding = new Melding();
        UtskriftsloopTraad t1 = new UtskriftsloopTraad(melding);
        MeldingsboksTraad t2 = new MeldingsboksTraad(t1, melding);

        t1.start();
        t2.start();
    }
}
