import java.io.Serializable;

public class MessageClient implements Serializable  {

	private static final long serialVersionUID = 1L;

	private Action action;

	public MessageClient(Action action) {
		setAction(action);
	}

	public void setAction(Action action) {
		this.action = action;
	}
	
	public Action getAction() {
		return action;
	}
}