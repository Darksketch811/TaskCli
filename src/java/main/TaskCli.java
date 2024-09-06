
public class TaskCli {

	public static void main(String[] args) {
		
		TaskManager tm = new TaskManager();

		if (args.length < 1) {
			System.out.println("Usage: TaskCli <command> [arguments]");
			return;
		}

		String command = args[0];

		switch (command) {
		case "add":{
			if(args.length > 1 ) {
			tm.add(args[1]);
			}else {
				System.out.println("missing description");
			}
		}
			break;
		case "update":{
			if(args.length > 2 ) {
				tm.update(args[1],args[2]);
				}else {
					System.out.println("missing description");
				}
		}
			break;
		case "delete":{
			if(args.length > 1 ) {
				tm.delete(args[1]);
				}else {
					System.out.println("missing description");
				}
		}
			break;
		case "mark-in-progress":{

			if(args.length > 1 ) {
				tm.inProgress(args[1]);
				}else {
					System.out.println("missing description");
				}
		
		}
			break;
		case "mark-done":{

			if(args.length > 1 ) {
				tm.markdone(args[1]);
				}else {
					System.out.println("missing description");
				}
		
		}
			break;
		case "list":{
			if(args.length > 1) {
			tm.list(args[1]);
			} else {
			tm.list();	
			}
		}
			break;
		

		default: {
			System.out.println("help");
		}
		}
		
		tm.saveTasks();
	}

}
