package no.hvl.dat108.oblig2.oppg2;

import java.util.LinkedList;
import java.util.Queue;

public class HamburgerBrett {

    private Queue<Hamburger> hamburgerBrett = new LinkedList<>();
    private int kapasitet;
    private int burgerNr = 1;

    public HamburgerBrett(int kapasitet) {
        this.kapasitet = kapasitet;
    }

    public synchronized void leggPaa(Hamburger h, String kokkNavn) {
        while (hamburgerBrett.size() == kapasitet) {
            IO.println(kokkNavn + " [kokk] " + "klar med hamburger, men brettet er fullt. Venter.");
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        hamburgerBrett.add(h);
        IO.println(kokkNavn + " [kokk] legger på hamburger " + h + ". Brett: " + hamburgerBrett);
        notifyAll();
    }

    public synchronized Hamburger taAv(String servitorNavn) {
        while (hamburgerBrett.isEmpty()) {
            IO.println(servitorNavn + " [servitør] ønsker å ta av hamburger, men brett tomt. Venter.");
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        Hamburger h = hamburgerBrett.remove();
        IO.println(servitorNavn + " [servitør] tar burger " + h + ". Brett: " + hamburgerBrett);
        notifyAll();
        return h;
    }

    public synchronized int nesteNr() {
        return burgerNr++;
    }
}



