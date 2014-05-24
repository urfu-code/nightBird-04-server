package defpac;

import java.io.Closeable;
import java.io.IOException;

public class Destroyter {

	public void close(Closeable closeable) {
		try {
			closeable.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
