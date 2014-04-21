import java.io.Serializable;

public class Response implements Serializable {

	private static final long serialVersionUID = 1L;
	private Action m_action;
	private String m_response;

	public Response(Action action) {
		m_action = action;
		m_response = "Action";		
	}

	public Action GetAction() {
		return m_action;
	}

	public String GetResponse() {
		return m_response;
	}

}
