package Network;

import java.io.Serializable;

public class NetCreateInfo implements Serializable {
	private static final long serialVersionUID = 210420142;
	private String m_name;
	
	public NetCreateInfo(String name){
		m_name = name;
	}
	
	public String getName(){
		return m_name;
	}
	
	public void setName(String name){
		m_name = name;
	}
}
