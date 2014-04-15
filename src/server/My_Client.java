package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import wood.Action;
import wood.Direction;
import mouse.My_Mouse;

public class My_Client {
	private static Socket socket;
	private static ObjectInputStream inStream;
	private static ObjectOutputStream outStream;

	public static void main(String[] args){
		String name = "Mouse";
		My_Mouse mouse = new My_Mouse();
		try{
			socket = new Socket("localhost", 25436);
			outStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			MessageServer messageServer = new MessageServer("createWoodman", name);
			outStream.writeObject(messageServer);
			outStream.flush();
			Action action = Action.Ok;
			while (!(action == Action.WoodmanNotFound) && !(action == Action.Finish)) {
				Direction direction = mouse.NextMove(action);
				MessageServer message = new MessageServer("move", name, direction);
				outStream.writeObject(message);
				outStream.flush();
				inStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				MessageClient messageClient = (MessageClient) inStream.readObject();
				action = messageClient.getAction();
				if(action == Action.WoodmanNotFound) System.out.println("Мышь умерла!:(");
				if(action == Action.Finish) System.out.println("Мышь дошла до финиша!:)");
			}
		}catch (IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException ex){
				ex.printStackTrace();
		}
		finally{
			closeObject(socket);		
			closeObject(outStream);
			closeObject(inStream);
		}
	}
	
	public static void closeObject(Closeable object){
		if (object != null){
			try{
				object.close();
			} catch(IOException ex){
				ex.printStackTrace();
			}
		}
	}

}
