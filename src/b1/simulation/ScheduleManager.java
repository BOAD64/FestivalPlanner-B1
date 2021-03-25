package b1.simulation;

import b1.schedule.AppointmentAbstract;
import b1.schedule.GeneralAppointment;
import b1.schedule.Schedule;
import b1.school.person.Person;
import b1.school.person.Student;
import b1.school.person.Teacher;
import b1.simulation.NPC.NPC;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class ScheduleManager
{
    private ArrayList<AppointmentAbstract> appointmentsSortedByBeginTime;
    private ArrayList<AppointmentAbstract> appointmentsSortedByEndTime;
    private int nextStartingAppointmentIndex;
    private int nextEndingAppointmentIndex;
    private Clock clock;
    private HashMap<Person, NPC> npcs;
    private HashMap<String, Target> targets;

    private int lastHour;
    private int lastMinute;

    public ScheduleManager(Schedule schedule, Clock clock, ArrayList<NPC> npcs, HashMap<String, Target> targets) {
        this.clock = clock;
        this.npcs = new HashMap<>();
        this.appointmentsSortedByBeginTime = new ArrayList<>(schedule.getAppointments());
        this.appointmentsSortedByEndTime = new ArrayList<>(schedule.getAppointments());
        this.targets = targets;

        for(NPC npc : npcs)
        {
            this.npcs.put(npc.getPerson(), npc);
        }
    }

    public void init()
    {
        this.appointmentsSortedByBeginTime.sort(Comparator.comparing(AppointmentAbstract::getStartTime));
        this.appointmentsSortedByEndTime.sort(Comparator.comparing(AppointmentAbstract::getEndTime));
        this.nextStartingAppointmentIndex = 0;
        this.nextEndingAppointmentIndex = 0;
        this.lastHour = this.clock.getCurrentTime().getHour();
        this.lastMinute = this.clock.getCurrentTime().getMinute();
    }

    public void update(double deltaTime)
    {
        LocalTime currentTime = this.clock.getCurrentTime();

        if(currentTime.getHour()==this.lastHour && currentTime.getMinute()==this.lastMinute)
        {
            return;
        }

        if(isLocalDateTimeEqual(currentTime, this.appointmentsSortedByBeginTime.get(this.nextStartingAppointmentIndex).getStartTime()))
        {
            ArrayList<AppointmentAbstract> appointmentsStartingNow = new ArrayList<>();
            for(int index = this.nextStartingAppointmentIndex; index<this.appointmentsSortedByBeginTime.size(); index++)
            {
                AppointmentAbstract appointment = this.appointmentsSortedByBeginTime.get(index);
                if(this.isLocalDateTimeEqual(appointment.getStartTime(), currentTime))
                {
                    appointmentsStartingNow.add(appointment);
                }
                else
                {
                    this.nextStartingAppointmentIndex = index;
                    break;
                }
            }
            this.summonNPCS(appointmentsStartingNow);
        }

        if(isLocalDateTimeEqual(currentTime, this.appointmentsSortedByEndTime.get(this.nextEndingAppointmentIndex).getEndTime()))
        {
            ArrayList<AppointmentAbstract> appointmentsEndingNow = new ArrayList<>();
            for(int index = this.nextEndingAppointmentIndex; index<this.appointmentsSortedByEndTime.size(); index++)
            {
                AppointmentAbstract appointment = this.appointmentsSortedByEndTime.get(index);
                if(this.isLocalDateTimeEqual(appointment.getEndTime(), currentTime))
                {
                    appointmentsEndingNow.add(appointment);
                }
                else
                {
                    this.nextEndingAppointmentIndex = index;
                    break;
                }
            }
            this.dismiss(appointmentsEndingNow);
        }

        this.lastHour = currentTime.getHour();
        this.lastMinute = currentTime.getMinute();
    }

    private void summonNPCS(ArrayList<AppointmentAbstract> appointments)
    {
        for(AppointmentAbstract appointment : appointments)
        {
            for(Person person : appointment.getPersons())
            {
                NPC result = this.npcs.get(person);
                if(result != null)
                {
                    Target target = this.targets.get(appointment.getLocation().getName());
                    if(target != null){
                        result.setTarget(target);
                    }
                }
            }
        }
    }

    private void dismiss(ArrayList<AppointmentAbstract> appointments)
    {
        for(AppointmentAbstract appointment : appointments)
        {
            for(Person person : appointment.getPersons())
            {
                NPC result = this.npcs.get(person);
                if(result != null)
                {
                    result.sendToStandardTarget();
                }
            }
        }
    }

    private boolean isLocalDateTimeEqual(LocalTime localTime1, LocalTime localTime2)
    {
        return localTime1.getHour()==localTime2.getHour() && localTime1.getMinute()==localTime2.getMinute();
    }
}
