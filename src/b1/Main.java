package b1;

import b1.io.SchoolFile;
import b1.schedule.Lesson;
import b1.school.School;
import b1.school.group.Group;
import b1.school.person.Student;
import b1.school.person.Teacher;
import b1.school.room.Classroom;
import javafx.application.Application;
import javafx.stage.Stage;

import java.time.LocalTime;

public class Main extends Application {
    private School school;

    public Main() {

//        SchoolFile.setFilePath(System.getProperty("user.dir") + System.getProperty("file.separator") + "/data.dat");
//        if (SchoolFile.getSchool() == null) {
//            SchoolFile.setSchool(new School("Hogwarts"));
//        }
//        this.school = SchoolFile.getSchool();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            School testSchool = new School("Hogwarts");

            Group studentGroup = new Group("23TIVT1B1");

            testSchool.addStudent(new Student("Casper", (short)18, "Man", (short)0, studentGroup));
            testSchool.addStudent(new Student("Jochem", (short)20, "Man", (short)0, studentGroup));
            testSchool.addStudent(new Student("Jeroen", (short)19, "Man", (short)0, studentGroup));
            testSchool.addStudent(new Student("Tom", (short)19, "Man", (short)0, studentGroup));
            testSchool.addStudent(new Student("Lieselotte", (short)18, "Vrouw", (short)0, studentGroup));

            testSchool.addTeacher(new Teacher("Luuk", (short)19, "Man", "Alles"));
            testSchool.addTeacher(new Teacher("Johan", (short)35, "Man", "Programmeren"));
            testSchool.addTeacher(new Teacher("Edwin", (short)45, "Man", "Senior"));

            testSchool.addRoom(new Classroom(10,10, "LA101", 30));
            testSchool.addRoom(new Classroom(10,10, "LA102", 30));

            studentGroup.addStudent(testSchool.getStudents().get(0));
            studentGroup.addStudent(testSchool.getStudents().get(1));
            studentGroup.addStudent(testSchool.getStudents().get(2));
            studentGroup.addStudent(testSchool.getStudents().get(3));
            testSchool.addGroup(studentGroup);

            testSchool.getSchedule().getAppointments().add(new Lesson("OGP1", LocalTime.of(9, 0), LocalTime.of(10, 0), testSchool.getRooms().get(0), "Hallo", studentGroup, testSchool.getTeachers().get(0)));
            testSchool.getSchedule().getAppointments().add(new Lesson("OGP1", LocalTime.of(9, 0), LocalTime.of(10, 0), testSchool.getRooms().get(1), "Hallo", studentGroup, testSchool.getTeachers().get(0)));
            testSchool.getSchedule().getAppointments().add(new Lesson("OGP1", LocalTime.of(9, 30), LocalTime.of(10, 30), testSchool.getRooms().get(1), "Hallo", studentGroup, testSchool.getTeachers().get(0)));

            SchoolFile.setFilePath(System.getenv("APPDATA")
                    + System.getProperty("file.separator")
                    + "HogwartsSimulator"
                    + System.getProperty("file.separator")
                    + "data.dat");

            if(SchoolFile.getSchool() == null)
            {
                SchoolFile.setSchool(testSchool);
            }

            MainController mainController = new MainController();
            mainController.show();
            mainController.onClose(event -> {SchoolFile.save();});
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
