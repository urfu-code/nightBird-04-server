package Network;

import java.io.Serializable;

import wood01.Action;

public class WoodResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String responseType;
	private boolean woodmanCreated;
	private Action action;
	private String error;
	
	public WoodResponse(String string) {
		responseType = "error";
		this.error = string;
	}
	
	public WoodResponse(Action action) {
		responseType = "action";
		this.action = action;
		woodmanCreated = true;
		
	}
	public Action getAction() {
		return action;
	}
	
	public boolean isWoodmanCreated() {
		return woodmanCreated;
	}
	
	public String getError() {
		return error;
	}
	
	public String getResponseType() {
		return responseType;
	}
}
