package no.hvl.dat108.f17a;

//NB! Må være Integer, ikke int, for at Spring skal kunne gjøre data-binding
public record Person(String fornavn, String etternavn, Integer fodselsaar) {}
