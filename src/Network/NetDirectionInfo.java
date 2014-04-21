package Network;

import java.io.Serializable;

import WoodEngine.Direction;

public class NetDirectionInfo implements Serializable {
	private static final long serialVersionUID = 210420143;
	private String m_name;
	private Direction m_dir;
	
	public NetDirectionInfo(String name, Direction dir){
		m_name = name;
		m_dir = dir;
	}
	
	public String getName() {
		return m_name;
	}
	public void setName(String m_name) {
		this.m_name = m_name;
	}
	
	public Direction getDirection() {
		return m_dir;
	}
	public void setDirection(Direction m_dir) {
		this.m_dir = m_dir;
	}
	
}
