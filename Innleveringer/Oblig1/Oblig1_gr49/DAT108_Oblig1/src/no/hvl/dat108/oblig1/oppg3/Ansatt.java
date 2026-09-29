package no.hvl.dat108.oblig1.oppg3;

public class Ansatt {
    private final String fornavn;
    private final String etternavn;
    private final Kjonn kjonn;
    private final String stilling;
    private int aarslonn;

    public Ansatt(String fornavn, String etternavn, Kjonn kjonn, String stilling, int aarslonn) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.kjonn = kjonn;
        this.stilling = stilling;
        this.aarslonn = aarslonn;
    }

    public int getAarslonn() {
        return aarslonn;
    }

    public String getStilling() {
        return stilling;
    }

    public Kjonn getKjonn() {
        return kjonn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public String getFornavn() {
        return fornavn;
    }


    public void setAarslonn(int aarslonn) {
        this.aarslonn = aarslonn;
    }

    @Override
    public String toString() {
        return "Ansatt{" +
                "fornavn='" + fornavn + '\'' +
                ", etternavn='" + etternavn + '\'' +
                ", kjonn=" + kjonn +
                ", stilling='" + stilling + '\'' +
                ", aarslonn=" + aarslonn +
                '}';
    }
}
