package no.hvl.dat108.oblig1.oppg1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Oppg1a {

public static void main (String[] args) {
    List<String> listen = Arrays.asList("10", "1", "20", "110", "21", "12");
    IO.println("Original liste:     " + listen);

    Collections.sort(listen, (s1, s2) -> Integer.parseInt(s1) - Integer.parseInt(s2));
    IO.println("Sortert stigende verdi:     " + listen);

    }
}


