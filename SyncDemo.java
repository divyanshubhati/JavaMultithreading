class Counter {
	int count;
	public synchronized void increment() {
		//here count ++ is split into 2 operations , 
		// while the thread 1 increment the value , the thread 2 might read the old value which may lead to false result
		count++;
	}
}
public class SyncDemo {
	public static void main(String[] args) throws InterruptedException {
		Counter c = new Counter();
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				for(int i=0; i<1000; i++) {
					c.increment();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				for(int i=0; i<1000; i++) {
					c.increment();
				}
			}
		});
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.print(c.count);
	}
}
