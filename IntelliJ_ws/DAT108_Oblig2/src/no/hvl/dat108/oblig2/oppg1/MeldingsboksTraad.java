package no.hvl.dat108.oblig2.oppg1;

import javax.swing.*;

public class MeldingsboksTraad extends Thread {

    private UtskriftsloopTraad t;
    private Melding melding;

    public MeldingsboksTraad(UtskriftsloopTraad t, Melding melding) {
        this.t = t;
        this.melding = melding;
    }

    @Override
    public void run() {
        while (true) {
            String input = JOptionPane.showInputDialog("Skriv inn melding, quit for å avslutte");
            if (input == null || input.equals("quit")) {
                t.stopp();
                break;
            } else {
                melding.setTekst(input);
            }
        }
    }
}
