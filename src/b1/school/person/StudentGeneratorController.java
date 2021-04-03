package b1.school.person;

import b1.Controller;
import b1.ErrorMessage;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StudentGeneratorController implements Controller {
    private StudentGeneratorView view;

    public StudentGeneratorController() {
        this.view = new StudentGeneratorView();
    }

    @Override
    public void show() {
        this.show(null);
    }

    @Override
    public void show(Stage ownerStage) {
        if(!this.view.getStage().isShowing()) {
            Stage stage = this.view.getStage();
            this.view.getCancelButton().setOnAction(e -> this.view.getStage().close());
            this.view.getSaveButton().setOnAction((e -> this.generate()));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(ownerStage);
            stage.show();
        }
    }

    private void generate() {
        try {
            short amount = Short.parseShort(this.view.getTextField().getText());
            StudentGenerator generator = new StudentGenerator(amount);
            this.view.getStage().close();
        } catch (Exception e){
            ErrorMessage.show();
        }
    }

    @Override
    public void onClose(EventHandler<WindowEvent> eventEventHandler) {
        this.view.getStage().setOnHidden(eventEventHandler);
    }
}
