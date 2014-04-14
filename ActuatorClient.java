import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ActuatorClient {
	private static Socket sock;
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;

	public static void main(String[] args) throws IOException {
		String name = "Norushka";
		Mouse norushka = new Mouse(name);

		sock = new Socket("localhost", 32015);
		oos = new ObjectOutputStream(new BufferedOutputStream(sock.getOutputStream()));

		MessageToServer toSend = new MessageToServer("createWoodman", name);
		oos.writeObject(toSend);
		oos.flush();

		Direction latestDirection = norushka.NextMove(Action.Ok);	
		Direction newDirection = Direction.None;
		Action newAction = Action.Ok;

		while (!(newAction == Action.WoodmanNotFound) && !(newAction == Action.Finish)) {
			MessageToServer moveItForMe = new MessageToServer("move", name, latestDirection);
			oos.writeObject(moveItForMe);
			oos.flush();

			ois = new ObjectInputStream(new BufferedInputStream(sock.getInputStream()));
			try {
				MessageToClient recieved = (MessageToClient) ois.readObject();
				newAction = recieved.getAction();
				newDirection = norushka.NextMove(newAction);
				latestDirection = newDirection;
				
				if (recieved.getLifeCount() < 2)
					norushka.nameOfAlghoritm = "Back to life";	
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}

		if (newAction == Action.WoodmanNotFound)
			System.out.println("Мышь умерла");
		if (newAction == Action.Finish)
			System.out.println("Finish!");

		try {
			sock.close();		
			ois.close();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
