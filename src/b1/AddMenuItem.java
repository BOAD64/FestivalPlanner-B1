package b1;

import javafx.stage.Stage;

public class AddMenuItem
{
    private Class<? extends Controller> controller;
    private String title;
    private Stage ownerStage;

    public AddMenuItem(Class<? extends Controller> controller, String title, Stage ownerStage) {
        this.controller = controller;
        this.title = title;
        this.ownerStage = ownerStage;
    }

    public Class<? extends Controller> getController() {
        return this.controller;
    }

    public void setController(Class<? extends Controller> controller) {
        this.controller = controller;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void onclick() {
        try {
            Controller controller = this.controller.getConstructor().newInstance();
            controller.show(this.ownerStage);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.title;
    }
}
