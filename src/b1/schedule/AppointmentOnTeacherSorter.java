package b1.schedule;

import b1.io.SchoolFile;
import b1.school.person.Teacher;
import b1.school.room.Room;

import java.util.ArrayList;
import java.util.HashMap;

public class AppointmentOnTeacherSorter implements AppointmentSorter
{
    @Override
    public HashMap<Object, ArrayList<AppointmentAbstract>> sort(Schedule schedule) {
        HashMap<Object, ArrayList<AppointmentAbstract>> result = new HashMap<>();

        for (Teacher teacher : SchoolFile.getSchool().getTeachers()) {
            result.put(teacher, new ArrayList<>());
        }

        for (AppointmentAbstract appointment : schedule.getAppointments()) {
            if (appointment.getStartTime() == null || appointment.getEndTime() == null) {
                continue;
            }
            if (appointment.getLocation() == null) {
                continue;
            }

            ArrayList<AppointmentAbstract> appointments;
            if(appointment.getClass().equals(Lesson.class)) {
                appointments = result.get(((Lesson) appointment).getTeacher());
            }
            else{
                appointments = result.get("Rest");
                if(appointments == null)
                {
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
        return "Teacher";
    }
}
