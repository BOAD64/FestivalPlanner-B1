package b1.schedule;

import b1.Controller;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public abstract class AppointmentControllerAbstract implements Controller {

    public abstract void onClose(EventHandler<WindowEvent> eventEventHandler);
}
