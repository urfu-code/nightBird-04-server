import java.io.Serializable;


public class MessageToClient implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Action action;
	private int lifeCount;

	public MessageToClient(Action act, int lifeCount) {
		setAction(act);
		setLifeCount(lifeCount);
	}

	public Action getAction() {
		return action;
	}
	public void setAction(Action act) {
		this.action = act;
	}

	public int getLifeCount() {
		return lifeCount;
	}
	public void setLifeCount(int lifeCount) {
		this.lifeCount = lifeCount;
	}

}
