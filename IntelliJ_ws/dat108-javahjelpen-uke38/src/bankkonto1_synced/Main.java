package bankkonto1_synced;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {

        //Trådsikker med synchronized
        //Ikke beskyttet mot overtrekk, se utskrift under kjøring.
		Bankkonto konto = new Bankkonto(1000);
		
		//Far setter inn totalt 2000,- i 5 omganger
		Thread far = new Thread(() -> { 
			for (int i=1; i<=5; i++) {
				konto.settInnKr(400);
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {}
			}
		});
		far.start();
		
		//Mor tar ut totalt 2500,- i 10 omganger
		Thread mor = new Thread(() -> { 
			for (int i=1; i<=10; i++) {
				konto.taUtKr(250);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {}
			}
		});
		mor.start();

		far.join();
		mor.join();
		
		System.out.println(konto + " til slutt. Skal være kr 500,00.");
	}

}
