package bankkonto2_sync_wait_notify;

public class Bankkonto {
	
	private double saldo;

	public Bankkonto(double startsaldo) {
		this.saldo = startsaldo;
	}
	
	public synchronized void settInnKr(double belop) {
		saldo += belop;
		notifyAll();
		System.out.println(this);
	}
	
	public synchronized void taUtKr(double belop) {
		while (saldo - belop < 0) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		saldo -= belop;
		System.out.println(this);
	}

	@Override
	public String toString() {
		return String.format("Bankkonto [saldo = kr %.2f]", saldo);
	}
}
