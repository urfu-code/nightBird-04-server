import java.io.Closeable;
import java.io.IOException;

public class Close {
	
	protected static void tryClose (Closeable closeable) {		
		try {
			((java.io.Closeable) closeable).close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
