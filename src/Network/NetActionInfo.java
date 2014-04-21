package Network;

import java.io.Serializable;

import WoodEngine.Action;

public class NetActionInfo implements Serializable {
	private static final long serialVersionUID = 210420141;
	private String m_name;
	private Action m_act;
	
	public NetActionInfo(String name, Action act){
		m_name = name;
		m_act = act;
	}
	
	public String getName() {
		return m_name;
	}
	public void setName(String m_name) {
		this.m_name = m_name;
	}
	
	public Action getAction() {
		return m_act;
	}
	public void setAction(Action m_act) {
		this.m_act = m_act;
	}
}
