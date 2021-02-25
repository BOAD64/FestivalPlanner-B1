package b1;

import b1.schedule.Schedule;
import b1.schedule.ScheduleController;
import b1.school.School;
import b1.school.classroom.Classroom;
import b1.school.classroom.ClassroomController;
import b1.school.group.Group;
import b1.school.group.GroupController;
import b1.school.person.Student;
import b1.school.person.StudentController;

import java.util.ArrayList;

public class MainController implements Controller {

    private MainView view;
    private School school;

    public MainController(School school) {
        this.school = school;
    }

    @Override
    public void show() {
        //this.view = new MainView();
        //this.view.getStage().show();
//
//        groupTest();
//        classroomTest();
        studentTest();
//        scheduleTest();
    }

    public void scheduleTest() {
        ScheduleController scheduleController = new ScheduleController(school.getSchedule());
        scheduleController.show();
    }


    public void groupTest() {
        Student student1 = new Student("bob", (short) 2, "attack helicoptor", (short) 1, "lol");
        Student student2 = new Student("ha", (short) 2, "attack helicoptor", (short) 2, "lol");
        Student student3 = new Student("bb", (short) 3, "attack helicoptor", (short) 3, "lol");
        Student student4 = new Student("yb", (short) 6, "attack helicoptor", (short) 4, "lol");
        Student student5 = new Student("ue", (short) 1, "attack helicoptor", (short) 5, "lol");

        ArrayList<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);

        Group group = new Group("lol");
        group.setStudents(students);

        GroupController groupController = new GroupController(group);
        groupController.show();
    }

    public void classroomTest() {
        Classroom c = new Classroom(420, 666, "lol34", 69);
        ClassroomController classroomController = new ClassroomController(c);
        classroomController.show();
    }

    public void studentTest() {
        Student student = new Student("bob", (short) 2, "attack helicoptor", (short) 3, "lol");
        StudentController studentController = new StudentController(student);
        studentController.show();
    }
}
