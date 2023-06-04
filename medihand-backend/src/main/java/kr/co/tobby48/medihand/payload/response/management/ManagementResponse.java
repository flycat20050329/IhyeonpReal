package kr.co.tobby48.medihand.payload.response.management;

import java.util.ArrayList;
import java.util.List;

public class ManagementResponse {
	private String name;
	private List<ManagementCommonResponse> tasks;
	
	public ManagementResponse(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.tasks = new ArrayList<ManagementCommonResponse>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ManagementCommonResponse> getTasks() {
		return tasks;
	}
	public void setTasks(List<ManagementCommonResponse> tasks) {
		this.tasks = tasks;
	}

}
