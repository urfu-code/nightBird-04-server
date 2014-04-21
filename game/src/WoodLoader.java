
import java.io.IOException;
import java.io.InputStream;

/**
 * ��������� ����
 */
public interface WoodLoader {
	/**
	 * ������� ��������� ���� �� ������ �� ������.
	 * @param stream ����� � ����������� � ����.
	 * @return ���
	 */
	Wood Load(InputStream stream) throws IOException, CodeException;
}
