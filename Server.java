import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Close {
	private static ObjectOutputStream out;
	private static ObjectInputStream in;
	private static ServerSocket serverSocket;
	private static Socket socket;

	public static void main(String[] args) throws CodeException, IOException {
		File file=new File("world.txt");
		InputStream instream = new FileInputStream(file);
		PrintableWoodLoader W = new PrintableWoodLoader();
		PrintableWood wood = W.PrintableWoodLoad(instream,System.out);
		Request m_request;
		Response m_response;	

		System.out.println("Server started");
		serverSocket = new ServerSocket(324);
		socket = serverSocket.accept();
		in = new ObjectInputStream(socket.getInputStream());
		Action currentAction = Action.Ok;
		String currentRequest;

		try {

			while((currentAction != Action.Finish) && (currentAction != Action.WoodmanNotFound)) {
				m_request = (Request)((ObjectInputStream) in).readObject();
				currentRequest=m_request.GetMethod();

				switch (currentRequest) {

				case "CreateWoodman" :
					wood.createWoodman(m_request.GetName(), m_request.GetStart(), m_request.GetFinish());
					break;

				case "MoveWoodman" :
					currentAction = wood.move(m_request.GetName(), m_request.GetDirection());
					out = new ObjectOutputStream(socket.getOutputStream());
					m_response = new Response(currentAction);
					out.writeObject(m_response);
					out.flush();
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
			tryClose(serverSocket);
			tryClose(socket);
			tryClose(out);
			tryClose(in);
			tryClose(instream);
		}
	}

	
}
