
class Factory {
	int count;
	
	//create lock
	boolean isPacketAvailable = false;
	
	// synchronized is required for wait and notify
	synchronized void produce() throws InterruptedException {
		while(isPacketAvailable) {
			// wait is the object function, put the corresponding thread to hold
			wait();
		}
		count++;
		System.out.println("Producing Packet Number >> " + count);
		isPacketAvailable = true;
		// notify is the object function that awakes the waiting treads for this object
		notify();
	}
	synchronized void consume() throws InterruptedException {
		while(!isPacketAvailable) {
			wait();
		}
		System.out.println("Consuming Packet Number >> " + count);
		isPacketAvailable = false;
		notify();
	}
}




class Producer implements Runnable {
	Factory factory;
	Producer(Factory f){
		factory = f;
		Thread t = new Thread(this, "Producer");
		t.start();
	}

	@Override
	public void run() {
		while(true) {
			try {
				factory.produce();
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}



class Consumer implements Runnable {
	Factory factory;
	Consumer(Factory f){
		factory = f;
		Thread t = new Thread(this, "Consumer");
		t.start();
	}
	@Override
	public void run() {
		while(true) {
			try {
				factory.consume();
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}



public class ProducerConsumer {
	
	public static void main(String[] args) {
		Factory bottleFactory = new Factory();
		new Producer(bottleFactory);
		new Consumer(bottleFactory);

	}
}
