package defpac;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class My_Client {
	private static Socket socket;
	private static ObjectInputStream inStream;
	private static ObjectOutputStream outStream;

	public static void main(String[] args){
		String name = "Mouse";
		Deadmau5 mouse = new Deadmau5();
		try{
			socket = new Socket("localhost", 25436);
			outStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			Chatbox messageServer = new Chatbox("create mau5", name);
			outStream.writeObject(messageServer);
			outStream.flush();
			Action action = Action.Ok;
			while (!(action == Action.WoodmanNotFound) && !(action == Action.Finish)) {
				Direction direction = mouse.NextMove(action);
				Chatbox message = new Chatbox("move mau5", name, direction);
				outStream.writeObject(message);
				outStream.flush();
				inStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				Chatbox messageClient = (Chatbox) inStream.readObject();
				action = messageClient.getAction();
				if(action == Action.WoodmanNotFound) System.out.println("POTRA4ENO!:(");
				if(action == Action.Finish) System.out.println("Mau5 finished!:)");
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