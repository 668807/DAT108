package no.hvl.dat108.f19b;

import java.util.List;
import java.util.Map;

public class Varer {

    public static Map<Integer, Vare> vareliste = Map.of(
            123, new Vare(123, "Brun genser", 599.0),
            456, new Vare(456, "Blå bukse", 849.50),
            789, new Vare(789, "Fin skjorte", 1600.0)
    );
}
