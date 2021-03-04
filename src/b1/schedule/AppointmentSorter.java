package b1.schedule;

import java.util.ArrayList;
import java.util.HashMap;

public interface AppointmentSorter
{
    HashMap<Object, ArrayList<AppointmentAbstract>> sort(Schedule schedule);
}
