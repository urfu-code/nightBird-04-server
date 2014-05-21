import java.io.Serializable;


public class MessageServer implements Serializable {
	private static final long serialVersionUID = 1L;
	private Action m_action;
	
	public MessageServer (Action action) {
		this.m_action = action;
	}
	
	public Action getAction() {
		return this.m_action;
	}
	
	public void setAction(Action action) {
		this.m_action = action;
	}
}
