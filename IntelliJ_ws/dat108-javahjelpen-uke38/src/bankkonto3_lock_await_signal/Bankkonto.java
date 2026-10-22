package bankkonto3_lock_await_signal;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bankkonto {
	
	private double saldo;

    private Lock laasen = new ReentrantLock();
    private Condition pengerSattInn = laasen.newCondition();

    public Bankkonto(double startsaldo) {
		this.saldo = startsaldo;
	}
	
	public void settInnKr(double belop) {
        laasen.lock();
        try {
            saldo += belop;
            pengerSattInn.signalAll();
            System.out.println(this);
        } finally {
            laasen.unlock();
        }
	}
	
	public void taUtKr(double belop) {
        laasen.lock();
        try {
            while (saldo < belop) {
                pengerSattInn.await();
            }
            saldo -= belop;
            System.out.println(this);
        } catch (InterruptedException e) {
        } finally {
            laasen.unlock();
        }
	}

	@Override
	public String toString() {
		return String.format("Bankkonto [saldo = kr %.2f]", saldo);
	}
}
