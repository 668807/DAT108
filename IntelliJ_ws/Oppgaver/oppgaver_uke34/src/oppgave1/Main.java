package oppgave1;


public class Main {

    static void skrivUtTallene(int[] tabell, Tallformat format) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < tabell.length; i++) {
            sb.append(format.somStreng(tabell[i]));
            if (i < tabell.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        System.out.println(sb);
    }

    public static void main(String[] args) {
        Tallformat format = new Stjerneformat();
        System.out.println(format.somStreng(3));
        System.out.println(format.somStreng(4));
        System.out.println(format.somStreng(5));


        Tallformat format2 = new Romertallformat();
        System.out.println(format2.somStreng(3));
        System.out.println(format2.somStreng(4));
        System.out.println(format2.somStreng(5));

        int[] tabell = {2, 4, 1};
        skrivUtTallene(tabell, new Stjerneformat());
        skrivUtTallene(tabell, new Romertallformat());
    }
}
