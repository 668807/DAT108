package no.hvl.dat108.f19b;

import java.util.*;

public class Handlekurv {

    private Map<Integer, Integer> kurv = new HashMap<>();

    public boolean isTom() {
        return kurv.isEmpty();
    }

    public double getTotal() {
        return getVarer().stream()
                .mapToDouble(v -> v.vare().pris() * v.antall())
                .sum();
    }

    public List<VareOgAntall> getVarer() {

        List<VareOgAntall> varer = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : kurv.entrySet()) {
            int varenr = entry.getKey();
            int antall = entry.getValue();
            Vare vare = Varer.vareliste.get(varenr);
            varer.add(new VareOgAntall(vare, antall));
        }
        return varer;
    }

    public void leggTilEtAntall(int varenr, int antall) {

// Alternativ 1: Gamlemåten
//        if (innhold.containsKey(varenr)) {
//            innhold.put(varenr, innhold.get(varenr)+antall);
//        } else {
//            innhold.put(varenr, antall);
//        }
// Alternativ 2: Litt bedre
//        innhold.putIfAbsent(varenr, 0);
//        innhold.put(varenr, innhold.get(varenr) + antall);

// Alternativ 3: Nyemåten
        kurv.merge(varenr, antall, Integer::sum);
    }

    public void fjern(int varenr) {
        kurv.remove(varenr);
    }
}
