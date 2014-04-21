
import java.io.Serializable;

public class Request implements Serializable {

	private static final long serialVersionUID = 1L;	
	private String method;
	private String m_name;
	private Direction m_direction;
	private Point  m_start;
	private Point  m_finish;

	public Request(String name, Point start, Point finish) {		
		m_name = name;
		m_start = start;
		m_finish = finish;
		method = "CreateWoodman";
	}

	public Request(String name, Direction direction) {
		m_name = name;
		m_direction = direction;
		method = "MoveWoodman";
	}

	public String GetMethod() {
		return method;
	}
	
	public String GetName() {
		return m_name;
	}

	public Point GetStart() {
		return m_start;
	}

	public Point GetFinish() {
		return m_finish;
	}

	public Direction GetDirection() {
		return m_direction;
	}	
}
