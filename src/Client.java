import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	
	private static Socket socket;
	private static ObjectInputStream in_stream;
	private static ObjectOutputStream out_stream;

	public static void main(String[] args) throws Exception{
		
		try{ 
			String name = "Armstrong";
			MyMouse mouse = new MyMouse();	
			socket = new Socket("localhost", 12345);
			out_stream = new ObjectOutputStream(socket.getOutputStream());
			
			MessageServer messageServer = new MessageServer("createWoodman", name);
			out_stream.writeObject(messageServer);
			out_stream.flush();
			
			Action action = Action.Ok;
			while (true) {
				if (action == Action.Finish) {
					System.out.println(name+" came to the Finish!");
					break;
				}
				if (action == Action.WoodmanNotFound) {
					System.out.println(name+"'s dead.");
					break;
				}
				
				Direction direction = mouse.NextMove(action);
				MessageServer message = new MessageServer("move", name, direction);
				out_stream.writeObject(message);
				out_stream.flush();
				in_stream = new ObjectInputStream(socket.getInputStream());
				MessageClient messageClient = (MessageClient) in_stream.readObject();
				action = messageClient.getAction();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			socket.close();		
			out_stream.close();	
			in_stream.close();	
		}
	}
}