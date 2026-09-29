package no.hvl.dat108.oblig2.oppg3;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class HamburgerBrett {

    private BlockingQueue<Hamburger> brett;
    private AtomicInteger burgerNr = new AtomicInteger(1);

    public HamburgerBrett(int kapasitet) {
        brett = new LinkedBlockingQueue<>(kapasitet);
    }

    public  void leggPaa(String kokkNavn) {
        Hamburger h = new Hamburger(burgerNr.getAndIncrement());
        try {
            brett.put(h);
        } catch (InterruptedException e) {}
        IO.println(kokkNavn + " [kokk] legger på hamburger " + h + ". Brett: " + brett);
    }

    public Hamburger taAv(String servitorNavn) {
        Hamburger h = null;
        try {
            h = brett.take();
        } catch (InterruptedException e) {}
        IO.println(servitorNavn + " [servitør] tar av hamburger " + h + ". Brett: " + brett);
        return h;
    }
}



