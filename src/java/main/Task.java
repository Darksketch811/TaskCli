import java.time.LocalDateTime;

import org.json.JSONObject;

public class Task {
	
	private static int  lastId = 0 ;
	private int id;
	private String description;
	private Status status;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	public Task(String description){
		this.setId(++lastId);
		this.setDescription(description);
		this.setStatus(Status.TODO);
		this.setCreatedDate(LocalDateTime.now());
		this.setUpdatedDate(LocalDateTime.now());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	public static Task fromJson(JSONObject json) {
//		System.out.println(json);
		Task task = new Task((String) json.getString("description"));

		task.setId(json.getInt("id"));
		task.setStatus(Status.valueOf(json.getString("status").toUpperCase()));
		task.setCreatedDate(LocalDateTime.parse(json.getString("createdTime")));
		task.setUpdatedDate(LocalDateTime.parse(json.getString("updatedTime")));
		
		if(lastId < json.getInt("id"))
			lastId = json.getInt("id");
		
		return task;
	}
}
