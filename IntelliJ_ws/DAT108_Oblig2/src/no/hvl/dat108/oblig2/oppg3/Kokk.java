package no.hvl.dat108.oblig2.oppg3;

import java.util.Random;

public class Kokk extends Thread {

    private HamburgerBrett brett;
    private Random random = new Random();

    private static final int MIN_SEK = 2;
    private static final int MAX_SEK = 6;

    public Kokk(HamburgerBrett brett, String navn) {
        super(navn);
        this.brett = brett;
    }

    @Override
    public void run() {
        while (true) {
            int sekunder = random.nextInt(MIN_SEK, MAX_SEK + 1);
            try {
                sleep(sekunder * 1000);
            } catch (InterruptedException e) {}
            brett.leggPaa(getName());
        }
    }
}
