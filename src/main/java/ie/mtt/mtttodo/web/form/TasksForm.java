package ie.mtt.mtttodo.web.form;
 
import java.util.List;
import java.util.ArrayList;

import ie.mtt.mtttodo.services.Task;

public class TasksForm {
	private List<Task> tasks;
	private String errorText;
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public void add(Task task) {
		if(this.tasks == null) {
			tasks = new ArrayList<Task>();
		}
		this.tasks.add(task);
	}

	public String getErrorText() {
		return this.errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
}
