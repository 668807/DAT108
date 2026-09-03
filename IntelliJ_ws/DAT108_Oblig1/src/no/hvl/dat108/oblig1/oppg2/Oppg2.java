package no.hvl.dat108.oblig1.oppg2;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


public class Oppg2 {

    private static void skrivUtAlle(List<Ansatt> ansatte) {
    for (Ansatt a : ansatte) {
        IO.println(a);
    }
    }

    
    private static void lonnsoppgjor(List<Ansatt> ansatte, Function<Ansatt, Integer> fu) {
        for (Ansatt a : ansatte) {
            int nyLonn = fu.apply(a);
            a.setAarslonn(nyLonn);
        }
    }

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

        // 1: Et fast kronetillegg
        List<Ansatt> ansatte1 = lagAnsatte();
        Function<Ansatt, Integer> kronetillegg = a -> a.getAarslonn() + 10000;
        IO.println("Før tillegg: ");
        skrivUtAlle(ansatte1);
        IO.println("\nEtter kronetillegg: ");
        lonnsoppgjor(ansatte1, kronetillegg);
        skrivUtAlle(ansatte1);

        // 2: Et fast prosenttillegg
        Function<Ansatt, Integer> prosenttillegg = a -> (int) (a.getAarslonn() * 1.05);
        List<Ansatt> ansatte2 = lagAnsatte();
        IO.println("\nEtter prosenttillegg: ");
        lonnsoppgjor(ansatte2, prosenttillegg);
        skrivUtAlle(ansatte2);

        // 3: Fast kronetillegg hvis lav lønn
        List<Ansatt> ansatte3 = lagAnsatte();
        double snittlonn = ansatte3.stream()
                .mapToInt(Ansatt::getAarslonn)
                .average()
                .orElse(0);
        int grense = (int) (snittlonn * 0.85);
        Function<Ansatt, Integer> krHvisLav = a -> a.getAarslonn() <= grense ? a.getAarslonn() + 25000 : a.getAarslonn();
        IO.println("\nEtter kronetillegg hvis lav lønn :");
        lonnsoppgjor(ansatte3, krHvisLav);
        skrivUtAlle(ansatte3);

        // 4. Fast prosenttillegg hvis mann:
        List<Ansatt> ansatte4 = lagAnsatte();
        Function<Ansatt, Integer> tilleggMann = a -> a.getKjonn() == Kjonn.MANN ? (int) (a.getAarslonn() * 1.05) : a.getAarslonn();
        IO.println("\nEtter prosenttillegg hvis mann :");
        lonnsoppgjor(ansatte4, tilleggMann);
        skrivUtAlle(ansatte4);
    }

}
