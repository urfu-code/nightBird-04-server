package Network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import Bot.Mouse;
import WoodEngine.Action;

public class WoodClient {
	@SuppressWarnings("resource")
	public static void main(String[] args){
		String name = "dyatel";
		try{
			Socket serverConnection = new Socket("localhost", 39588);
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(serverConnection.getInputStream()));
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(serverConnection.getOutputStream()));
			Mouse mouse = new Mouse(name);
			NetDirectionInfo dirInfo = new NetDirectionInfo(name, mouse.NextMove(Action.Ok));
			try{
				NetCreateInfo crInf = new NetCreateInfo(name);
				oos.writeObject(crInf);
				oos.writeObject(dirInfo);
				oos.flush();
				while(true){
					Object inpMessage = ois.readObject();
					if(inpMessage.getClass() == NetActionInfo.class){
						NetActionInfo actInf = (NetActionInfo) inpMessage;
						if(actInf.getAction() == Action.Finish
								|| actInf.getAction() == Action.WoodmanNotFound) break;
						dirInfo.setDirection(mouse.NextMove(actInf.getAction()));
						oos.writeObject(dirInfo);
						oos.flush();
					} else{
						throw new IOException("Illegal input object class");
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				tryClose(serverConnection);
				tryClose(ois);
				tryClose(oos);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
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
