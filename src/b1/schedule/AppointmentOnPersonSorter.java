package b1.schedule;

import b1.io.SchoolFile;
import b1.school.person.Person;

import java.util.ArrayList;
import java.util.HashMap;

public class AppointmentOnPersonSorter implements AppointmentSorter {

    /**
     * This method sorts the appointments on Person
     *
     * @param schedule is the schedule that need its appointments sorted
     * @return the sorted appointments
     */
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

            for (Person person : appointment.getPersons()) {
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
