package b1.schedule;

import b1.io.ImageFile;
import b1.school.person.Person;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GeneralAppointmentView extends AppointmentViewAbstract {
    private Stage stage;
    private ListView<Person> participantsList;
    private Button saveButton;
    private Button cancelButton;
    private ComboBox<Person> personComboBox;
    private Button personAddButton;
    private Button personRemoveButton;

    public GeneralAppointmentView() {
        super();
    }

    public Stage getStage() {
        if (this.stage == null) {
            this.stage = new Stage();
            this.createStage();
        }

        return this.stage;
    }

    private void createStage() {
        VBox vBox = new VBox();
        this.saveButton = new Button("Opslaan");
        this.cancelButton = new Button("Annuleren");

        HBox buttonsBox = new HBox();
        buttonsBox.getChildren().add(this.cancelButton);
        buttonsBox.getChildren().add(this.saveButton);

        HBox addPersonBox = new HBox();
        this.personComboBox = new ComboBox<>();
        this.personAddButton = new Button("Voeg toe");
        this.personRemoveButton = new Button("Verwijder");

        addPersonBox.getChildren().add(this.personComboBox);
        addPersonBox.getChildren().add(this.personAddButton);
        addPersonBox.getChildren().add(this.personRemoveButton);

        this.participantsList = new ListView<>();

        VBox mainPersonBox = new VBox();
        mainPersonBox.getChildren().add(addPersonBox);
        mainPersonBox.getChildren().add(this.participantsList);

        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        buttonsBox.setSpacing(10);
        buttonsBox.setAlignment(Pos.CENTER_RIGHT);

        vBox.getChildren().add(super.createAbstractAppointmentElements());
        vBox.getChildren().add(mainPersonBox);
        vBox.getChildren().add(buttonsBox);

        Scene scene = new Scene(vBox);
        this.stage.setScene(scene);
        this.stage.getIcons().add(ImageFile.getLogo());
    }

    public ListView<Person> getParticipantsList() {
        return this.participantsList;
    }

    public void setParticipantsList(ListView<Person> participantsList) {
        this.participantsList = participantsList;
    }

    public Button getSaveButton() {
        return this.saveButton;
    }

    public Button getCancelButton() {
        return this.cancelButton;
    }

    public ComboBox<Person> getPersonComboBox() {
        return this.personComboBox;
    }

    public Button getPersonAddButton() {
        return this.personAddButton;
    }

    public Button getPersonRemoveButton() {
        return this.personRemoveButton;
    }
}
