package defpac;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class My_Server {
	
	private static ServerSocket serverSocket;
	private static Socket socket;
	private static FileInputStream stream;
	private static ObjectInputStream inStream;
	private static ObjectOutputStream outStream;
	
	public static void main(String[] args) {
		/*File file = new File("myWood.txt");
		Point start = new Point(1, 1);
		Point finish = new Point (3, 5);
		 */
		/*Point start = new Point(6,1);
		Point finish = new Point(6,6);
		File file = new File("myWood1.txt");
		 */
		Point start = new Point(1,1);
		Point finish = new Point(5,5);
		File file = new File("simple forest.txt");
		Action action = Action.Ok;
		try {
			serverSocket = new ServerSocket(25436);
			socket = serverSocket.accept();
			System.out.println("Client connected");
			stream = new FileInputStream(file);
			MyWoodLoader loader = new MyWoodLoader();
			MyPrintableWood wood = loader.Load(stream, System.out);
			inStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			try {
				while((action != Action.WoodmanNotFound) && (action != Action.Finish)) {
					Chatbox messageServer = (Chatbox)inStream.readObject();
					if (messageServer.getCommand().equals("create mau5")) {
							wood.createWoodman(messageServer.getName(), start, finish);
					}
					if (messageServer.getCommand().equals("move mau5")) {
							action = wood.move(messageServer.getName(), messageServer.getDirection());
							outStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
							Chatbox messageClient = new Chatbox(action);
							outStream.writeObject(messageClient);
							outStream.flush();
						}
					}
			} catch (IOException e) {
				e.printStackTrace();
			}catch(ClassNotFoundException ex){
				ex.printStackTrace();
			}
			finally {
				closeObject(serverSocket);
				closeObject(socket);			
				closeObject(stream);			
				closeObject(inStream);
				closeObject(outStream);							
			}
		} catch (IOException e) {
			e.printStackTrace();
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