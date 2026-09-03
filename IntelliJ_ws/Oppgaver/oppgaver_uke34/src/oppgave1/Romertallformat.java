package oppgave1;

public class Romertallformat implements Tallformat {

    @Override
    public String somStreng(int tall) {
        String[] romertall = {"I", "II", "III", "IV", "V"};
        return romertall[tall - 1];
    }
}
