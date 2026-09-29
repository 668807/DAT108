package no.hvl.dat108.oblig2.oppg2;

public class Hamburger {
    private final int nr;

    public Hamburger(int nr) {
        this.nr = nr;
    }

    public int getNr() {
        return nr;
    }


    @Override
    public String toString() {
        return "◖" + nr + "◗";
    }
}
