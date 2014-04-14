import java.io.Serializable;

public class MessageToServer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String methodName;
	private String woodmanName;
	private Direction direction;

	public MessageToServer(String method, String name) {
		setMethodName(method);
		setWoodmanName(name);
	}
	
	public MessageToServer(String method, String name, Direction dir) {
		setMethodName(method);
		setWoodmanName(name);
		setDirection(dir);
	}

	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String method) {
		this.methodName = method;
	}

	public String getWoodmanName() {
		return woodmanName;
	}
	public void setWoodmanName(String name) {
		this.woodmanName = name;
	}

	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction dir) {
		this.direction = dir;
	}
	
	

}
