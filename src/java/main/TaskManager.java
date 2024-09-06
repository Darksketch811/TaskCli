
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class TaskManager {

	ArrayList<Task> tasks = new ArrayList<>();

	public TaskManager() {
		loadTasks();
	}

	private void loadTasks() {

		if (!new File("task.json").exists()) {
			try {
				new File("task.json").createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String taskJsonText = "";

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("task.json"))) {

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				taskJsonText += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!taskJsonText.isEmpty()) {

			JSONArray taskJson = new JSONArray(taskJsonText);

			if (taskJson.length() != 0)
				for (int i = 0; taskJson.length() > i; i++) {
					tasks.add(Task.fromJson(taskJson.getJSONObject(i)));
				}
		}
	}

	public void add(String description) {
		Task task = new Task(description);
		tasks.add(task);
	}

	public void update(String id, String description) {
		for (Task task : tasks) {
			if (task.getId() == Integer.valueOf(id)) {
				task.setDescription(description);
				task.setUpdatedDate(LocalDateTime.now());
				break;
			}
		}
	}

	public void delete(String id) {
		int index = 0;
		for (Task task : tasks) {
			if (task.getId() == Integer.valueOf(id)) {
				index = tasks.indexOf(task);
				break;
			}
		}

		tasks.remove(index);
	}

	public void inProgress(String id) {
		for (Task task : tasks) {
			if (task.getId() == Integer.valueOf(id)) {
				task.setStatus(Status.IN_PROGRESS);
				break;
			}
		}
	}

	public void markdone(String id) {
		for (Task task : tasks) {
			if (task.getId() == Integer.valueOf(id)) {
				task.setStatus(Status.DONE);
				break;
			}
		}
	}

	public void list() {
		for (Task task : tasks) {
			System.out.println(task.getId() + "\t" + task.getDescription() + "\t" + task.getStatus());
		}
	}
	
	public void list(String status) {
		for (Task task : tasks) {
			if(task.getStatus().toString().equalsIgnoreCase(status))
			System.out.println(task.getId() + "\t" + task.getDescription() + "\t" + task.getStatus());
		}
	}

	public void saveTasks() {
		JSONArray taskArr = new JSONArray();
		for (Task task : tasks) {
			JSONObject taskObj = new JSONObject();
			taskObj.put("description", task.getDescription());
			taskObj.put("id", task.getId());
			taskObj.put("status", task.getStatus().toString());
			taskObj.put("createdTime", task.getCreatedDate().toString());
			taskObj.put("updatedTime", task.getUpdatedDate().toString());
			taskArr.put(taskObj);
		}

		try (FileWriter fw = new FileWriter(new File("task.json"))) {
			fw.write(taskArr.toString());
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
