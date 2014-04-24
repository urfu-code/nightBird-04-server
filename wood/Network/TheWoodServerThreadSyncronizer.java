package Network;

import java.util.ArrayList;

public class TheWoodServerThreadSyncronizer implements Runnable {
	
	private ArrayList<Thread> clients;
	
	public TheWoodServerThreadSyncronizer(ArrayList<Thread> clients) {
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
				System.out.println("Что то пошло не так");
			}
		}
	}
	
	
}
