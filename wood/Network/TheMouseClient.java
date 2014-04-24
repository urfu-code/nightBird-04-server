package Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

import wood01.Action;
import wood03.TheMouse;

public class TheMouseClient {
	public static void main(String[] Args) throws IOException {
		System.out.println("Client started");
		Socket fromServer = null;
		ObjectInputStream objectReader = null;
		ObjectOutputStream objectWriter = null;
		WoodResponse response;
		MouseRequest request;
		try {
			fromServer = new Socket("localhost", 6789);
			objectWriter = new ObjectOutputStream(fromServer.getOutputStream());
			objectReader = new ObjectInputStream(fromServer.getInputStream());
			Random random = new Random();
			StringBuffer name = new StringBuffer();
			for (int i = 0; i < 6; i++) {
				name.append((char) Math.abs(random.nextInt(128)));
			}
			request = new MouseRequest(name.toString());
			TheMouse kolya = new TheMouse(name.toString());
			objectWriter.writeObject(request);
			objectWriter.flush();
			while (true) {
				response = (WoodResponse) objectReader.readObject();
				if (response.getResponseType().equals("action")) {
					if (response.getAction() == Action.ExitFound || response.getAction() == Action.WoodmanNotFound) {
						break;
					}
					request = new MouseRequest(kolya.getName(), kolya.NextMove(response.getAction()));
					objectWriter.writeObject(request);
					objectWriter.flush();
				}
				else {
					System.out.println("That is all..");
					break;
				}
			}
		}
		catch (ClassNotFoundException e) {
			System.out.println("Received bad object");
		} catch (IOException e) {
			System.out.println("Cannot create socket");
		} catch (Exception e) {
			System.out.println("Problems with mouse");
			e.printStackTrace();
		}
		finally {
			if (fromServer != null) {
				fromServer.close();
			}
			if (objectReader != null) {
				objectReader.close();
			}
			if (objectWriter != null) {
				objectWriter.close();
			}
		}
	}
}
