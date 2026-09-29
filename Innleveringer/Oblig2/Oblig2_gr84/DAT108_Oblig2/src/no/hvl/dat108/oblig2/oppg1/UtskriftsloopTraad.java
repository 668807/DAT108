package no.hvl.dat108.oblig2.oppg1;


public class UtskriftsloopTraad extends Thread {

    private Melding melding;
    private boolean fortsette = true;

    public void stopp() {
        fortsette = false;
    }

    public UtskriftsloopTraad(Melding melding) {
        this.melding = melding;
    }

    @Override
    public void run() {
        while (fortsette) {
            IO.println(melding.getTekst());
            try {
                sleep(3000);
            } catch (InterruptedException e) {
            }
        }
    }
}
