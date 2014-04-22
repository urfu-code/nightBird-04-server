import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends Close {
	
	private static Socket socket;
	private static ObjectInputStream in;
	private static ObjectOutputStream out;

	public static void main(String[] args) throws Exception{
		MyMouse Minni = new MyMouse("Minni");  
		Response m_response;
		Request m_request;
		System.out.println("Game started");	
			socket = new Socket("localhost", 324);
			out = new ObjectOutputStream(socket.getOutputStream());
			m_request = new Request("Minni", new Point(1,1), new Point(6,7));
			out.writeObject(m_request);
			out.flush();
			Action currentAction = Action.Ok;
			
			try {
				
				while ((currentAction != Action.Finish) && (currentAction != Action.WoodmanNotFound)) {
				Direction direction = Minni.NextMove(currentAction);
	            Request message = new Request("Minni", direction);
				out.writeObject(message);
				out.flush();
				in = new ObjectInputStream(socket.getInputStream());
				m_response = (Response) in.readObject();
				currentAction = m_response.GetAction();
			
				if ((currentAction == Action.WoodmanNotFound)) {
					System.out.println("Woodman not found");
				} 

				if (currentAction == Action.Finish) {
					System.out.println("You reached finish");
				}
			}
				System.out.println("Game over");	
			}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			tryClose(socket);
			tryClose(out);
			tryClose(in);
		}
	}

		
}
