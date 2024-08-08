
public class MVCPatternDemo {
    public static void main(String[] args) {
        Student student = new Student("1", "Konapala Aditya", "A+");

        StudentView view = new StudentView();

        StudentController controller = new StudentController(student, view);

        controller.updateView();

        controller.setStudentName("Pragnyalipsa Patra Konapala");
        controller.setStudentGrade("B+");

        controller.updateView();
    }
}
