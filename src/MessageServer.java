import java.io.Serializable;

public class MessageServer implements Serializable {

	private static final long serialVersionUID = 1L;

	private String method;
	private String name;
	private Direction direction;

	public MessageServer(String method, String name) {
		setMethod(method);
		setName(name);
	}

	public MessageServer(String method, String name, Direction direction) {
		setMethod(method);
		setName(name);
		setDirection(direction);
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getMethod() {
		return method;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}
}