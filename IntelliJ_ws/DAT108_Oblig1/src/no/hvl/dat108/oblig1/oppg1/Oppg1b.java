package no.hvl.dat108.oblig1.oppg1;

import java.util.function.BinaryOperator;

public class Oppg1b {
    public static int beregn(int a, int b, BinaryOperator<Integer> c) { return c.apply(a, b); }

    public static void main(String[] args) {
        BinaryOperator<Integer> summerFunksjon = (a, b) -> a + b;
        BinaryOperator<Integer> maxFunksjon = (a, b) -> Math.max(a, b);
        BinaryOperator<Integer> absFunksjon =  (a, b) -> Math.abs(a - b);

        int sum = beregn(12, 13, summerFunksjon);
        IO.println(sum);

        int max = beregn(-5, 3, maxFunksjon);
        IO.println(max);

        int abs = beregn(54, 45,  absFunksjon);
        IO.println(abs);
    }
}
