package b1.schedule;

import b1.Controller;

public class ScheduleController implements Controller
{
    private Schedule schedule;
    private ScheduleView view;

    public ScheduleController(Schedule schedule)
    {
        this.schedule = schedule;
        this.view = new ScheduleView();
    }

    @Override
    public void show() {
        if(!view.getStage().isShowing()){
            view.getStage().show();
        }
    }
}
