package b1.schedule;

import b1.io.SchoolFile;
import b1.school.group.Group;

import java.util.ArrayList;
import java.util.HashMap;

public class AppointmentOnGroupSorter implements AppointmentSorter {

    /**
     * This method sorts the appointments on StudentGroup
     *
     * @param schedule is the schedule that need its appointments sorted
     * @return the sorted appointments
     */
    @Override
    public HashMap<Object, ArrayList<AppointmentAbstract>> sort(Schedule schedule) {
        HashMap<Object, ArrayList<AppointmentAbstract>> result = new HashMap<>();

        for (Group group : SchoolFile.getSchool().getGroups()) {
            result.put(group, new ArrayList<>());
        }

        for (AppointmentAbstract appointment : schedule.getAppointments()) {
            if (appointment.getStartTime() == null || appointment.getEndTime() == null) {
                continue;
            }
            if (appointment.getLocation() == null) {
                continue;
            }

            ArrayList<AppointmentAbstract> appointments;
            if (appointment.getClass().equals(Lesson.class)) {
                appointments = result.get(((Lesson) appointment).getGroup());
            } else {
                appointments = result.get("Rest");
                if (appointments == null) {
                    result.put("Rest", new ArrayList<>());
                    appointments = result.get("Rest");
                }
            }
            appointments.add(appointment);
        }

        return result;
    }

    @Override
    public String toString() {
        return "Group";
    }
}
