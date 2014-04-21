import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] Args) throws Exception {

		Socket socket = null;
		Response m_response;
		Request m_request;
		System.out.println("Game started");
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		
		try {
			socket = new Socket("localhost", 12345);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			MyMouse Minni = new MyMouse("Minni");  
			m_request = new Request("Minni", new Point(1,1), new Point(1,5));			
			out.writeObject(m_request);
			out.flush();

			while (true) {
				m_response = (Response) in.readObject();
				if (m_response.GetResponse().equals("Action")) {
					m_request = new Request(Minni.GetName(), Minni.NextMove(m_response.GetAction()));
					out.writeObject(m_request);
					out.flush();
				}
				else {
					System.out.println("Game over");
					break;
				}
			}		
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

	private static void tryClose(Closeable closeable) {
		try {
			closeable.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
