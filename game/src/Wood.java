import java.io.IOException;


public interface Wood {
	/**
	 * Создает нового лесного жителя.
	 * 
	 * @param name имя лесного жителя
	 * @param start место появления
	 * @throws CodeException 
	 * @throws IOException 
	 */
	void createWoodman(String name, Point start,Point finish) throws CodeException, IOException;
	
	/**
	 * Перемещает лесного жителя.
	 * 
	 * @param name имя лесного жителя
	 * @param direction направление перемещения
	 * @return результат перемещения
	 * @throws IOException 
	 * @throws CodeException 
	 */
	Action move(String name, Direction direction) throws IOException, CodeException;

}
