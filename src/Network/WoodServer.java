package Network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import WoodEngine.Action;
import WoodEngine.Point;
import WoodEngine.PrintableWood;
import WoodEngine.Wood;
import WoodEngine.WoodLoader;

public class WoodServer {
	
	@SuppressWarnings("resource")
	public static void main(String args[]){
		Action act;
		int steps = 0;
		WoodLoader wl = new WoodLoader();
		try{
			Wood wood = (PrintableWood) wl.LoadPrntbleWood(new FileInputStream("maze"), System.out);
			ServerSocket server = new ServerSocket(39588);
			Socket inpClient = server.accept();
			System.err.println("Connection established");
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(inpClient.getInputStream()));
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(inpClient.getOutputStream()));
			NetActionInfo action = new NetActionInfo(null, null);
			try{
				while(true){
					Object inpMessage = ois.readObject();
					if(inpMessage.getClass() == NetCreateInfo.class){
						NetCreateInfo create = (NetCreateInfo) inpMessage;
						wood.createWoodman(create.getName(), new Point(17, 13), new Point(1, 13));
						System.out.println("Woodman " + create.getName() + " created");
					}
					if(inpMessage.getClass() == NetDirectionInfo.class){
						NetDirectionInfo move = (NetDirectionInfo) inpMessage;
						act = wood.move(move.getName(), move.getDirection());
						steps++;
						action.setName(move.getName());
						action.setAction(act);
						oos.writeObject(action);
						oos.flush();
					} else{
						throw new IOException("Illegal input object class");
					}
					if(act == Action.Finish){
						System.out.println("YOU FINISHED in " + steps);
						break;
					}
					if(act == Action.WoodmanNotFound){
						System.out.println("LOL YOU DIED in " + steps);
						break;
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally{
				tryClose(server);
				tryClose(inpClient);
				tryClose(ois);
				tryClose(oos);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void tryClose(Closeable cl) {
		try{
			cl.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
}
