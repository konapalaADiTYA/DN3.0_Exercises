	class Employee {
	    private String employeeId;
	    private String name;
	    private String position;
	    private double salary;
	
	    public Employee(String employeeId, String name, String position, double salary) {
	        this.employeeId = employeeId;
	        this.name = name;
	        this.position = position;
	        this.salary = salary;
	    }
	
		public String getEmployeeId() {
			return employeeId;
		}
	
		public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId;
		}
	
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
	
		public String getPosition() {
			return position;
		}
	
		public void setPosition(String position) {
			this.position = position;
		}
	
		public double getSalary() {
			return salary;
		}
	
		public void setSalary(double salary) {
			this.salary = salary;
		}
	    
	}
	
	
	
	public class EmployeeManagementSystem {
	    private Employee[] employees;
	    private int size;
	    private int capacity;
	
	    public EmployeeManagementSystem(int capacity) {
	        this.capacity = capacity;
	        this.employees = new Employee[capacity];
	        this.size = 0;
	    }
	
	    public void addEmployee(Employee employee) {
	        if (size == capacity) {
	            System.out.println("Cannot add more employees, capacity reached.");
	            return;
	        }
	        else {
	        employees[size++] = employee;
	        }
	    }
	
	    public Employee searchEmployee(String employeeId) {
	        for (int i = 0; i < size; i++) {
	            if (employees[i].getEmployeeId()==employeeId) {
	                return employees[i];
	            }
	        }
	        return null;
	    }
	    public static void display_employee(Employee employee) {
	    	System.out.println("Employee details:-");
	    	System.out.println("ID: "+employee.getEmployeeId());
	    	System.out.println("Name: "+employee.getName());
	    	System.out.println("Position: "+employee.getPosition());
	    	System.out.println("Salary: "+employee.getSalary());
	    	System.out.println();
	    }
	
	    public void traverseEmployees() {
	        for (int i = 0; i < size; i++) {
	            display_employee(employees[i]);
	        }
	    }
	
	    public void deleteEmployee(String employeeId) {
	        int index = -1;
	        for (int i = 0; i < size; i++) {
	            if (employees[i].getEmployeeId().equals(employeeId)) {
	                index = i;
	                break;
	            }
	        }
	        if (index == -1) {
	            System.out.println("Employee not found.");
	            return;
	        }
	        for (int i = index; i < size - 1; i++) {
	            employees[i] = employees[i + 1];
	        }
	        employees[--size] = null; 
	    }
	
	    public static void main(String[] args) {
	        EmployeeManagementSystem ems = new EmployeeManagementSystem(3);
	
	        Employee e1 = new Employee("01", "Aditya", "CEO",2150000);
	        Employee e2 = new Employee("02", "Pragnya", "Personal Assistant",50000);
	        Employee e3 = new Employee("03", "Sashi", "Garbage collector",600);
	        Employee e4 = new Employee("04","Sagar","Tea Maker",550);
	
	        ems.addEmployee(e1);
	        ems.addEmployee(e2);
	        ems.addEmployee(e3);
	
	        System.out.println("All Employees:");
	        ems.traverseEmployees();
	        
	        ems.addEmployee(e4);
	
	        System.out.println("\nSearching for Employee with ID '01':");
	        Employee searchResult = ems.searchEmployee("01");
	        if (searchResult != null) {
	            System.out.println("Employee found:");
	            display_employee(searchResult);
	        } else {
	            System.out.println("Employee not found.");
	        }
	
	        System.out.println("\nDeleting Employee with ID '03':");
	        ems.deleteEmployee("03");
	
	        System.out.println("\nAll Employees after deletion:");
	        ems.traverseEmployees();
	    }
	}
