

import java.io.Serializable;

public class Chatbox implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String command;
	String name;
	Direction direction;
	Action action;
	
	Chatbox (String command, String name) {
		this.command = command;
		this.name = name;
	}
	
	Chatbox (String command, String name, Direction direction) {
		this.command = command;
		this.name = name;
		this.direction = direction;
	}
	
	Chatbox (Action action) {
		this.action = action;
	}

	public String getName() {
		return name;
	}
	
	public String getCommand () {
		return command;
	}
	
	public Direction getDirection () {
		return direction;
	}
	
	public Action getAction () {
		return action;
	}
}
