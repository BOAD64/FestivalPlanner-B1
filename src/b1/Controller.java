package b1;

import javafx.stage.Stage;

public interface Controller {

    void show();

    /**
     * This method shows the stage defined in its class.
     *
     * @param ownerStage is the stage that called for this stage to open.
     */
    void show(Stage ownerStage);
}
