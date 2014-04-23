import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	
	private static ServerSocket serverSocket;
	private static Socket socket;
	private static FileInputStream stream;
	private static ObjectInputStream in_stream;
	private static ObjectOutputStream out_stream;
	
	public static void main(String[] args) throws Exception {

		try {
			serverSocket = new ServerSocket(12345);
			socket = serverSocket.accept();
			System.out.println("It works.");
			
			File file = new File("wood.txt");
			stream = new FileInputStream(file);
			MyWoodLoader loder = new MyWoodLoader();
			PrintableWood wood = loder.Load(stream, System.out);
			in_stream = new ObjectInputStream(socket.getInputStream());
			
			Point point_start = new Point(1,1);
			Point point_finish = new Point(6,2);
			Action action = Action.Ok;
			try {
				while((action != Action.WoodmanNotFound) && (action != Action.Finish)) {
					MessageServer messageServer = (MessageServer)in_stream.readObject();
					
					switch (messageServer.getMethod()) {
						case "createWoodman" :
							wood.createWoodman(messageServer.getName(), point_start, point_finish);
							break;
						case "move" :
							action = wood.move(messageServer.getName(), messageServer.getDirection());
							out_stream = new ObjectOutputStream(socket.getOutputStream());
							MessageClient messageClient = new MessageClient(action);
							out_stream.writeObject(messageClient);
							out_stream.flush();
							break;
						}
					}
			}
			finally {
				serverSocket.close();
				socket.close();			
				stream.close();	
				in_stream.close();
				out_stream.close();					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}