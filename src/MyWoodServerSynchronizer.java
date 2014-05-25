import java.util.Vector;


public class MyWoodServerSynchronizer implements Runnable {

	private Vector <Thread> clients;

	public MyWoodServerSynchronizer (Vector <Thread> clients) {
		this.clients = clients;
	}

	@Override
	public void run() {
		while (true) {
			try {
				synchronized (this) {
					notifyAll();
				}
				for (int i = 0; i < clients.size(); i++) {
					if(!clients.get(i).isAlive()) {
						clients.remove(i);
					}
				}
				Thread.sleep(500);
			}
			catch (InterruptedException e) {
				System.out.println("Oops, MyWoodServerSynchronizer has been crashed!");
				e.printStackTrace();
			}
		}
	}
}