package b1.schedule;

import b1.io.SchoolFile;
import b1.school.room.Room;

import java.util.ArrayList;
import java.util.HashMap;

public class AppointmentOnRoomSorter implements AppointmentSorter {

    /**
     * This method sorts the appointments on Room
     *
     * @param schedule is the schedule that need its appointments sorted
     * @return the sorted appointments
     */
    @Override
    public HashMap<Object, ArrayList<AppointmentAbstract>> sort(Schedule schedule) {
        HashMap<Object, ArrayList<AppointmentAbstract>> result = new HashMap<>();

        for (Room room : SchoolFile.getSchool().getRooms()) {
            result.put(room, new ArrayList<>());
        }

        for (AppointmentAbstract appointment : schedule.getAppointments()) {
            if (appointment.getStartTime() == null || appointment.getEndTime() == null) {
                continue;
            }
            if (appointment.getLocation() == null) {
                continue;
            }

            ArrayList<AppointmentAbstract> appointments = result.get(appointment.getLocation());
            appointments.add(appointment);
        }

        return result;
    }

    @Override
    public String toString() {
        return "Room";
    }
}
