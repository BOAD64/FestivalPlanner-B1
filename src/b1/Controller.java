package b1;

import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public interface Controller {

    void show();

    /**
     * This method shows the stage defined in its class.
     *
     * @param ownerStage is the stage that called for this stage to open.
     */
    void show(Stage ownerStage);

    void onClose(EventHandler<WindowEvent> eventEventHandler);
}
