package b1.simulation;

import b1.schedule.AppointmentAbstract;
import b1.schedule.Schedule;
import b1.school.person.Person;
import b1.simulation.NPC.CallbackNPC;
import b1.simulation.NPC.NPC;

import java.time.LocalTime;
import java.util.*;

public class ScheduleManager implements CallbackNPC
{
    private ArrayList<AppointmentAbstract> appointmentsSortedByBeginTime;
    private ArrayList<AppointmentAbstract> appointmentsSortedByEndTime;
    private int lastStartedAppointmentIndex;
    private int lastEndedAppointmentIndex;
    private Clock clock;
    private HashMap<Person, NPC> npcs;
    private HashMap<String, Target> targets;
    private int lastTimeDirection;

    private int lastHour;
    private int lastMinute;

    public ScheduleManager(Schedule schedule, Clock clock, ArrayList<NPC> npcs, HashMap<String, Target> targets) {
        this.clock = clock;
        this.npcs = new HashMap<>();
        this.appointmentsSortedByBeginTime = new ArrayList<>(schedule.getAppointments());
        this.appointmentsSortedByEndTime = new ArrayList<>(schedule.getAppointments());
        this.targets = targets;
        this.lastTimeDirection = 0;

        for (NPC npc : npcs) {
            this.npcs.put(npc.getPerson(), npc);
            npc.setCallbackNPC(this);
        }
    }

    public void init() {
        this.appointmentsSortedByBeginTime.sort(Comparator.comparing(AppointmentAbstract::getStartTime));
        this.appointmentsSortedByEndTime.sort(Comparator.comparing(AppointmentAbstract::getEndTime));
        this.lastStartedAppointmentIndex = -1;
        this.lastEndedAppointmentIndex = -1;
        this.lastHour = this.clock.getCurrentTime().getHour();
        this.lastMinute = this.clock.getCurrentTime().getMinute();
    }

    public void update(double deltaTime) {

        if(this.appointmentsSortedByBeginTime.size() == 0)
        {
            return;
        }

        LocalTime currentTime = this.clock.getCurrentTime();

        if (currentTime.getHour() == this.lastHour && currentTime.getMinute() == this.lastMinute) {
            return;
        }

        int timeDirection = (int) Math.round(deltaTime / Math.abs(deltaTime));

        if (timeDirection != this.lastTimeDirection && timeDirection == -1) {
            this.lastStartedAppointmentIndex += 1;
            this.lastEndedAppointmentIndex += 1;
        }

        int nextIndex = (this.lastStartedAppointmentIndex + timeDirection) % this.appointmentsSortedByBeginTime.size();

        if (nextIndex > -1 && nextIndex < this.appointmentsSortedByBeginTime.size()
                && isLocalDateTimeEqual(currentTime, this.appointmentsSortedByBeginTime.get(nextIndex).getStartTime())) {

            ArrayList<AppointmentAbstract> appointmentsStartingNow = new ArrayList<>();
            for (int index = nextIndex; -1 < index && index < this.appointmentsSortedByBeginTime.size(); index += timeDirection) {
                AppointmentAbstract appointment = this.appointmentsSortedByBeginTime.get(index);
                if (this.isLocalDateTimeEqual(appointment.getStartTime(), currentTime)) {
                    appointmentsStartingNow.add(appointment);
                }
                else {
                    break;
                }
                this.lastStartedAppointmentIndex = index;
            }

            if (timeDirection == 1) {
                this.summonNPCS(appointmentsStartingNow);
            }
            else {
                this.dismiss(appointmentsStartingNow);
            }
        }

        nextIndex = (this.lastEndedAppointmentIndex + timeDirection) % this.appointmentsSortedByEndTime.size();
        if (nextIndex > -1 && nextIndex < this.appointmentsSortedByEndTime.size()
                && isLocalDateTimeEqual(currentTime, this.appointmentsSortedByEndTime.get(nextIndex).getEndTime())) {

            ArrayList<AppointmentAbstract> appointmentsEndingNow = new ArrayList<>();
            for (int index = nextIndex; -1 < index && index < this.appointmentsSortedByEndTime.size(); index += timeDirection) {
                AppointmentAbstract appointment = this.appointmentsSortedByEndTime.get(index);
                if (this.isLocalDateTimeEqual(appointment.getEndTime(), currentTime)) {
                    appointmentsEndingNow.add(appointment);
                }
                else {
                    break;
                }
                this.lastEndedAppointmentIndex = index;
            }
            this.dismiss(appointmentsEndingNow);

            if (timeDirection == 1) {
                this.dismiss(appointmentsEndingNow);
            }
            else {
                this.summonNPCS(appointmentsEndingNow);
            }
        }

        if (this.isLocalDateTimeEqual(this.appointmentsSortedByEndTime.get(this.appointmentsSortedByEndTime.size() - 1).getEndTime(), currentTime)) {
            this.goHome(this.npcs.keySet());
        }

        this.lastHour = currentTime.getHour();
        this.lastMinute = currentTime.getMinute();
        this.lastTimeDirection = timeDirection;
    }

    @Override
    public void hasArrived(NPC npc, Target target) {
        if (target.equals(this.targets.get("LoadingZone"))) {
            npc.setIsHome(true);
        }
    }

    private void summonNPCS(ArrayList<AppointmentAbstract> appointments) {
        for (AppointmentAbstract appointment : appointments) {
            for (Person person : appointment.getPersons()) {
                NPC result = this.npcs.get(person);
                if (result != null) {
                    Target target = this.targets.get(appointment.getLocation().getName());
                    if (target != null) {
                        result.setIsHome(false);
                        result.setTarget(target);
                    }
                }
            }
        }
    }

    private void dismiss(ArrayList<AppointmentAbstract> appointments) {
        for (AppointmentAbstract appointment : appointments) {
            for (Person person : appointment.getPersons()) {
                NPC result = this.npcs.get(person);
                if (result != null) {
                    result.setIsHome(false);
                    result.sendToStandardTarget();
                }
            }
        }
    }

    private boolean isLocalDateTimeEqual(LocalTime localTime1, LocalTime localTime2) {
        return localTime1.getHour() == localTime2.getHour() && localTime1.getMinute() == localTime2.getMinute();
    }

    private void goHome(Collection<Person> persons) {
        for (Person person : persons) {
            NPC result = this.npcs.get(person);
            if (result != null) {
                Target target = this.targets.get("LoadingZone");
                result.setTarget(target);
            }
        }
    }
}
