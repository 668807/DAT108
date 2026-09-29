package no.hvl.dat108.oblig2.oppg1;

public class Melding {

    private String tekst = "Hallo Verden!";

    public synchronized String getTekst() {
        return tekst;
    }

    public synchronized void setTekst(String tekst) {
        this.tekst = tekst;
    }
}
