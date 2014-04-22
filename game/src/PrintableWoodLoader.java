import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class PrintableWoodLoader extends MyWoodLoader {

	
	public PrintableWood PrintableWoodLoad(InputStream inStream, OutputStream outStream) throws CodeException, IOException   {
		return new PrintableWood(getWood(inStream), outStream);
	}
}