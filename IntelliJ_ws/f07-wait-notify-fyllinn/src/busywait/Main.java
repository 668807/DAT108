package busywait;

public class Main {
	
	public static String melding;
	static final Object lock = new Object();
	
	static void main() {
		
		//En tråd som skriver ut melding på skjermen

		Thread printThread = new Thread(
				() -> {
					synchronized (lock) {
						while (melding == null) {
							try {
                                lock.wait(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
						}
					}
					IO.println(melding);
				});

		//En tråd som gir melding en verdi
		Thread giVerdiTraad = new Thread( () -> {
			synchronized (lock) {
				melding = "hallo";
				lock.notifyAll();
			}
		});

		printThread.start();
		giVerdiTraad.start();
	}
}
