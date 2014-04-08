package Network;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import wood01.Action;
import wood01.PrintableTheWood;
import wood01.TheWoodLoader;

public class TheWoodServer {
	
	public static void main(String[] Args) {
		ServerSocket server = null;
		Socket fromClient = null;
		ObjectOutputStream objectWriter = null;
		ObjectInputStream objectReader = null;
		MouseRequest request;
		WoodResponse response;
		File file = new File("wood\\wood03\\wood.txt");
		TheWoodLoader loader = new TheWoodLoader();
		try {
			server = new ServerSocket(6789);
			System.out.println("Server started!");
			try {
				PrintableTheWood wood = (PrintableTheWood) loader.Load(new FileInputStream(file),System.out);
				System.out.println("Waiting for connection...");
				fromClient = server.accept();
				System.out.println("Client connected!");
				objectReader = new ObjectInputStream(fromClient.getInputStream());
				objectWriter = new ObjectOutputStream(fromClient.getOutputStream());
				Action currentAction = Action.Ok;
				while (true) {
					if ((currentAction == Action.WoodmanNotFound)||(currentAction == Action.ExitFound)) {
						break;
					}
					request = (MouseRequest) objectReader.readObject();
					if (request.getRequestType().equals("create")) {
						// нужно описать исключение, которое поймЄт что вудман не создан
						wood.createWoodman(request.getName(), request.getStartPoint(), request.getFinishPoint());
					}
					else if (request.getRequestType().equals("move")) {
						currentAction = wood.move(request.getName(), request.getDirection());
					}
					response = new WoodResponse(currentAction);
					objectWriter.writeObject(response);
					objectWriter.flush();
				}
				System.out.println(currentAction.toString());
			}
			catch (ClassNotFoundException e) {
				System.out.println("тестова€ беда");
			}
			catch (IOException e) {
				System.out.println("всЄ, хватило с вуд лиента пакетов");
				e.printStackTrace();
			}
			catch (Exception e) {
				System.out.println("беда с лесом");
				objectWriter.writeObject(new WoodResponse("woodError"));
			}
			finally {
				if(objectWriter != null) {
					objectWriter.close();
				}
				if (server != null) {
					server.close();
				}
				if (fromClient != null) {
					fromClient.close();
				}
				if (objectReader != null) {
					objectReader.close();
				}
			}
		}
		catch (IOException e) {
			System.out.println("port is busy");
			System.exit(-1);
		}


	}
}
