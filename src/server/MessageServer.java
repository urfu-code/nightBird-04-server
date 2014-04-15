package server;

import java.io.Serializable;

import wood.Direction;

public class MessageServer implements Serializable {

	private static final long serialVersionUID = 1L;

	private String method;//название метода
	private String name;//имя персонажа
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

	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}
