package Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import wood01.Point;
import wood03.TheMouse;

public class TheMouseClient {
	public static void main(String[] Args) {
		System.out.println("Client started");
		Socket fromServer = null;
		ObjectInputStream objectReader = null;
		ObjectOutputStream objectWriter = null;
		WoodResponse response;
		MouseRequest request;
		try {
			fromServer = new Socket("localhost", 6789);
			objectWriter = new ObjectOutputStream(fromServer.getOutputStream());
			request = new MouseRequest("kolya", new Point(11,6), new Point(1,1));
			TheMouse kolya = new TheMouse("kolya");
			objectWriter.writeObject(request);
			objectWriter.flush();
			objectReader = new ObjectInputStream(fromServer.getInputStream());
			while (true) {
				response = (WoodResponse) objectReader.readObject();
				if (response.getResponseType().equals("action")) {
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
		}
	}
}
