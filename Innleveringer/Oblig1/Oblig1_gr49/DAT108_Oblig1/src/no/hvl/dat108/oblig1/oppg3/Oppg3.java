package no.hvl.dat108.oblig1.oppg3;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

public class Oppg3 {

    private static List<Ansatt> lagAnsatte() {
        return Arrays.asList(
                new Ansatt("Ole", "Olesen", Kjonn.MANN, "Utvikler", 500000),
                new Ansatt("Arne", "Arnesen", Kjonn.MANN, "Sjef", 800000),
                new Ansatt("Lise", "Lisesen", Kjonn.KVINNE, "Utvikler", 500000),
                new Ansatt("Per", "Persen", Kjonn.MANN, "Utvikler", 500000),
                new Ansatt("Ida", "Idasen", Kjonn.KVINNE, "Leder", 700000)
        );
    }

    public static void main(String[] args) {
        List<Ansatt> ansatte =  lagAnsatte();

        // a) Liste med etternavn
        List<String> etternavn = ansatte.stream()
                .map(Ansatt::getEtternavn)
                .toList();

        IO.println("a) Etternavn: " + etternavn);

        // b) Antall kvinner
        long antallKvinner = ansatte.stream()
                .filter(a -> a.getKjonn() == Kjonn.KVINNE)
                .count();

        IO.println("\nb) Antall kvinner: " + antallKvinner);

        // c) Gjennomsnittslønn til kvinnene
        double snittLonnKvinner = ansatte.stream()
                .filter(a -> a.getKjonn() == Kjonn.KVINNE)
                .mapToInt(Ansatt::getAarslonn)
                .average()
                .orElse(0.0);
        IO.println("\nc) Gjennomsnittslønn til kvinnene: " + snittLonnKvinner);

        // d) Lønnsøkning på 7% til sjefer
       ansatte.stream()
                .filter(a -> a.getStilling().toLowerCase().contains("sjef"))
                .forEach(a -> a.setAarslonn((int) (a.getAarslonn() * 1.07)));

       IO.println("\nd) Etter lønnsøkning til sjefer: ");
       ansatte.forEach(IO::println);

       // e) Om noen ansatte tjener mer en 800.000,-
        boolean noenOver800k = ansatte.stream()
                .anyMatch(a -> a.getAarslonn() > 800_000);
        IO.println("\ne) Om noen tjener mer enn 800.000,-: " + noenOver800k);

        // f) Utskrift uten å bruke løkke
        IO.println("\n Utskrift uten løkke: ");
        ansatte.forEach(System.out::println);

        // g) Ansatt med lavest lønn
        int lavesteLonn = ansatte.stream()
                .mapToInt(Ansatt::getAarslonn)
                .min()
                .orElse(0);

        List<Ansatt> lavestLonnede = ansatte.stream()
                .filter(a -> a.getAarslonn() == lavesteLonn)
                .toList();

        IO.println("\ng) Ansatte med lavest lønn: " +  lavestLonnede);

        // h) Summen av heltall i [1, 1000> delelig med 3 eller 5.
        int sum = IntStream.range(1, 1000)
                .filter(i -> i % 3 == 0 || i % 5 == 0)
                .sum();
        IO.println("\nh) Summen av heltall i [1, 1000> delelig med 3 eller 5: " + sum);
    }
}
