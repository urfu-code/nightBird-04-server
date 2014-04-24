package Network;

import java.io.Serializable;

import wood01.Direction;
import wood01.Point;

public class MouseRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String requestType;
	@Deprecated
	private Point startPoint;
	@Deprecated
	private Point finishPoint;
	private String mouseName;
	private Direction direction;
	
	@Deprecated
	public MouseRequest(String name,Point start, Point finish) {
		requestType = "create";
		startPoint = start;
		finishPoint = finish;
		mouseName = name;
	}
	
	public MouseRequest(String name) {
		mouseName = name;
		requestType = "create";
	}
	public MouseRequest(String name, Direction direction) {
		requestType = "move";
		this.direction = direction;
		mouseName = name;
	}
	
	public String getRequestType() {
		return requestType;
	}
	
	@Deprecated
	public Point getStartPoint() {
		return startPoint;
	}
	
	@Deprecated
	public Point getFinishPoint() {
		return finishPoint;
	}
	
	public String getName() {
		return mouseName;
	}
	
	public Direction getDirection() {
		return direction;
	}
}
