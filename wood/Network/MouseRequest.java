package Network;

import java.io.Serializable;

import wood01.Direction;
import wood01.Point;

public class MouseRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String requestType;
	private Point startPoint;
	private Point finishPoint;
	private String mouseName;
	private Direction direction;
	
	public MouseRequest(String name,Point start, Point finish) {
		requestType = "create";
		startPoint = start;
		finishPoint = finish;
		mouseName = name;
	}
	
	public MouseRequest(String name, Direction direction) {
		requestType = "move";
		this.direction = direction;
		mouseName = name;
	}
	
	public String getRequestType() {
		return requestType;
	}
	
	public Point getStartPoint() {
		return startPoint;
	}
	
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
