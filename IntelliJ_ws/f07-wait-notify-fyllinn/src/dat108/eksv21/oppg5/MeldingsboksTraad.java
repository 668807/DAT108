package dat108.eksv21.oppg5;

import javax.swing.*;

public class MeldingsboksTraad extends Thread {

    private UtskriftsloopTraad t;

    public MeldingsboksTraad(UtskriftsloopTraad t) {
        this.t = t;
    }

    @Override
    public void run() {
        JOptionPane.showMessageDialog(null, "Trykk OK for å avbryte utskriftstråden",
                "Avbryt", JOptionPane.PLAIN_MESSAGE);
        t.stopp();
    }

	
/*  Tips i oppgavetekst:

	JOptionPane.showMessageDialog(null, "Trykk OK for å avbryte utskriftstråden", 
			"Avbryt", JOptionPane.PLAIN_MESSAGE);	
 */
}
