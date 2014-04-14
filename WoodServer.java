import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.security.CodeSource;

public class WoodServer {

	private static ServerSocket ss;
	private static Socket sock;
	private static ObjectInputStream ois;
	private static ObjectOutputStream oos;
	private static FileInputStream stream;

	public static void main(String[] args) throws URISyntaxException, IOException {

		CodeSource codeSource = WoodServer.class.getProtectionDomain().getCodeSource();
		File file = new File(new File(codeSource.getLocation().toURI().getPath()).getParentFile().getPath(), "src/input.txt");
		Point start = new Point(1, 1);
		Point finish = new Point (7, 2);
		Action act = Action.Ok;
		int lifeCount = 3;

		try {
			ss = new ServerSocket(32015);
			sock = ss.accept();
			System.out.println("Client connected");
			stream = new FileInputStream(file);
			PrintableWoodLoader wl = new PrintableWoodLoader();
			PrintableWood wood = wl.Load(stream);
			
			ois = new ObjectInputStream(new BufferedInputStream(sock.getInputStream()));

			try {
				do {
					MessageToServer recieved = (MessageToServer) ois.readObject();
					switch (recieved.getMethodName()) {
					case "createWoodman" :
						wood.createWoodman(recieved.getWoodmanName(), start, finish);
						break;
					case "move" :
						act = wood.move(recieved.getWoodmanName(), recieved.getDirection());
						lifeCount = wood.getWoodman(recieved.getWoodmanName()).GetLifeCount();

						oos = new ObjectOutputStream(new BufferedOutputStream(sock.getOutputStream()));
						MessageToClient toSend = new MessageToClient(act, lifeCount);
						oos.writeObject(toSend);
						oos.flush();
						break;
					}
				} while (act != Action.WoodmanNotFound && act != Action.Finish);
				
							
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
						
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		finally {
				ss.close();
				sock.close();			
				stream.close();			
				ois.close();
				oos.close();							
			}

	}


}
