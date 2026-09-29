package no.hvl.dat108.oblig2.oppg2;

import java.util.Arrays;

public class Main {

    public static void skrivUtHeader(String[] kokker, String[] servitorer, int kapasitet) {
        IO.println("I denne simuleringen har vi: ");
        IO.println("    " + kokker.length + " kokker " + Arrays.toString(kokker));
        IO.println("    " + servitorer.length + " servitører " + Arrays.toString(servitorer));
        IO.println("    " + "Kapasiteten til brettet er " + kapasitet + " hamburgere.");
        IO.println("Vi starter...");

    }

    public static void main(String[] args) {
        final String[] kokker = {"Anne", "Erik", "Knut"};
        final String[] servitorer = {"Mia", "Per"};
        final int KAPASITET = 4;

        skrivUtHeader(kokker, servitorer, KAPASITET);

        HamburgerBrett brett = new HamburgerBrett(KAPASITET);

        for (String navn : kokker) {
            new Kokk(brett, navn).start();
        }

        for (String navn : servitorer) {
            new Servitor(brett, navn).start();
        }
    }
}
