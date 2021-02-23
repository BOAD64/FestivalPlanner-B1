package b1;

import b1.io.SchoolFile;
import b1.schedule.Schedule;
import b1.schedule.ScheduleController;
import b1.school.School;
import b1.school.person.Student;
import b1.school.room.Room;
import javafx.application.Application;

import static javafx.application.Application.launch;

public class Main {
    public static void main(String[] args) {
        System.out.println("development branch");
        SchoolFile.setSchool(new School());
        SchoolFile.getSchool().getPersons().add(new Student());
        SchoolFile.getSchool().getPersons().add(new Student());
        SchoolFile.getSchool().getPersons().add(new Student());
        SchoolFile.getSchool().getPersons().add(new Student());

        SchoolFile.getSchool().getRooms().add(new Room());
        SchoolFile.getSchool().getRooms().add(new Room());
        SchoolFile.getSchool().getRooms().add(new Room());

        startGUI();
    }

    public static void startGUI(){
        Application.launch(MainView.class);
    }
}
