class Task {
    private String taskId;
    private String taskName;
    private String status;

    public Task(String taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
 }

class Node {
    Task task;
    Node next;

    Node(Task task) {
        this.task = task;
        this.next = null;
    }
}

public class TaskManagementSystem {
    private Node head;

    public TaskManagementSystem() {
        this.head = null;
    }

    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }
    public static void display(Task temp) {
    	System.out.println("Task details:- Id: "+temp.getTaskId()+",Name: "+temp.getTaskName()+", Status: "+temp.getStatus());
    }
    
    public Task searchTask(String taskId) {
        Node temp = head;
        while (temp != null) {
            if (temp.task.getTaskId()==taskId) {
                return temp.task;
            }
            temp = temp.next;
        }
        return null;
    }

    public void traverseTasks() {
        Node temp = head;
        while (temp != null) {
        	display(temp.task);
        	temp = temp.next;
        }
    }

    public void deleteTask(String taskId) {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }
        if (head.task.getTaskId()==taskId) {
            head = head.next;
            return;
        }
        Node temp = head;
        while (temp.next != null && !(temp.next.task.getTaskId()==taskId)) {
            temp = temp.next;
        }
        if (temp.next == null) {
            System.out.println("Task not found.");
            return;
        }
        temp.next = temp.next.next;
    }

    public static void main(String[] args) {
        TaskManagementSystem tms = new TaskManagementSystem();

        Task t1 = new Task("01", "Task:1", "Complete");
        Task t2 = new Task("02", "Task:2", "Incomplete");
        Task t3 = new Task("03", "Task:3", "Complete");

        tms.addTask(t1);
        tms.addTask(t2);
        tms.addTask(t3);

        System.out.println("All Tasks:");
        tms.traverseTasks();

        System.out.println("\nSearching for Task with ID '02':");
        Task searchResult = tms.searchTask("02");
        if (searchResult != null) {
            System.out.println("Task Found!!");
            display(searchResult);
        } else {
            System.out.println("Task not found.");
        }

        System.out.println("\nDeleting Task with ID '02':");
        tms.deleteTask("02");

        System.out.println("\nAll Tasks after deletion:");
        tms.traverseTasks();
    }
}
