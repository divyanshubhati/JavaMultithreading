
class One extends Thread {
    public void run() {
    	for(int i = 0; i<5; i++) {
    		System.out.println("Hello " + Thread.currentThread().getPriority());
            try {
    			Thread.sleep(500);
    		} catch (InterruptedException e) {
    		}
    	}
    }
}
class Two implements Runnable {
    public void run(){
    	for(int i = 0; i<5; i++) {
    		System.out.println("Hi");
            try {
    			Thread.sleep(500);
    		} catch (InterruptedException e) {
    		}
    	}
    }
}
class Second {
    public static void main(String[] args) throws InterruptedException {
        One one = new One();
        Two two = new Two();
        
        Thread t = new Thread(two);
        
        t.setName("Hakunma");
        one.setName("Tata");
        t.getName();
        System.out.println(one.getName());
        System.out.println(t.getName());
        
        t.setPriority(Thread.MAX_PRIORITY);
        one.setPriority(1);
        // 1 is least 10 is max
        
        
        one.start();
        Thread.sleep(10); // remove this and the order will break
        t.start();
        
        t.join();
        one.join();
        System.out.println("Bye!");
        System.out.println(t.isAlive());
        System.out.println(one.isAlive());
    }
}