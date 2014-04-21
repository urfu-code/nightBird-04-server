import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static ObjectOutputStream out;
	private static ObjectInputStream in;
	private static ServerSocket serverSocket;
	private static Socket socket;

	public static void main(String[] Args) throws IOException, CodeException, ClassNotFoundException {

		File file=new File("world.txt");
		InputStream instream=new FileInputStream(file);
		PrintableWoodLoader W=new PrintableWoodLoader();
		PrintableWood wood=W.PrintableWoodLoad(instream,System.out);
		Request m_request;
		Response m_response;	

		try {
			System.out.println("Server started");
			serverSocket = new ServerSocket(12345);
			socket = serverSocket.accept();
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			Action currentAction = Action.Ok;
			String currentRequest;
			System.out.println("Connection is established");

			if ((currentAction == Action.WoodmanNotFound)) {
				System.out.println("Woodman not found");
				throw new CodeException("Woodman not found");
			} 

			if (currentAction == Action.Finish) {
				System.out.println("You reached finish");
			}

			while ((currentAction != Action.Finish)&&(currentAction != Action.WoodmanNotFound)) {
				m_request = (Request)in.readObject();
				currentRequest=m_request.GetMethod();

				switch (currentRequest) {

				case "CreateWoodman" : {
					wood.createWoodman(m_request.GetName(), m_request.GetStart(), m_request.GetFinish());
					break;
				}

				case "MoveWoodman" : {
					currentAction = wood.move(m_request.GetName(), m_request.GetDirection());
					m_response = new Response(currentAction);
					out.writeObject(m_response);
					out.flush();
					break;
				}
				}		
			}
			System.out.println(currentAction.toString());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			tryClose(serverSocket);
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
