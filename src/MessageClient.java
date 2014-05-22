import java.io.Serializable;

public class MessageClient implements Serializable {
	private static final long serialVersionUID = 3529679846350670758L;
	private String m_name;
	private String m_task;
	private Direction m_direction;
	
	public MessageClient (String name, String task) {
		this.m_name = name;
		this.m_task = task;
	}
	
	public MessageClient (String name, String task, Direction direction) {
		this.m_name = name;
		this.m_task = task;
		this.m_direction = direction;
	}
	
	public String getName() {
		return this.m_name;
	}
	
	public void setName(String name) {
		this.m_name = name;
	}
	
	public String getTask() {
		return this.m_task;
	}
	
	public void setTask(String task) {
		this.m_task = task;
	}
	
	public Direction getDirection() {
		return this.m_direction;
	}
	
	public void setDirection(Direction direction) {
		this.m_direction = direction;
	} 
}
