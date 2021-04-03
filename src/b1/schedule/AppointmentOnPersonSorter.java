package b1.schedule;

import b1.io.SchoolFile;
import b1.school.person.Person;
import b1.school.person.Student;
import b1.school.person.Teacher;

import java.util.ArrayList;
import java.util.HashMap;

public class AppointmentOnPersonSorter implements AppointmentSorter
{
    @Override
    public HashMap<Object, ArrayList<AppointmentAbstract>> sort(Schedule schedule) {
        HashMap<Object, ArrayList<AppointmentAbstract>> result = new HashMap<>();

        for (Person person : SchoolFile.getSchool().getPersons()) {
            result.put(person, new ArrayList<>());
        }

        for (AppointmentAbstract appointment : schedule.getAppointments()) {
            if (appointment.getStartTime() == null || appointment.getEndTime() == null) {
                continue;
            }
            if (appointment.getLocation() == null) {
                continue;
            }

            for(Person person : appointment.getPersons())
            {
                result.get(person).add(appointment);
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return "Person";
    }
}