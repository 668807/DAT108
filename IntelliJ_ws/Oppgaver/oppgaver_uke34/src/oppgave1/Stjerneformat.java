package oppgave1;

public class Stjerneformat implements Tallformat{
    @Override
    public String somStreng(int tall) {
        StringBuilder sb = new StringBuilder();
        for (int i =1; i<=tall; i++) {
            sb.append("*");
        }
        return sb.toString();
    }
}
