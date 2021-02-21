package b1.schedule;

import b1.View;
import b1.school.person.Person;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppointmentView implements View {

    private Stage stage;

    private TextField nameField;
    private Spinner<Integer> beginTimeHour;
    private Spinner<Integer> beginTimeMinute;
    private Spinner<Integer> endTimeHour;
    private Spinner<Integer> endTimeMinute;
    private TextField locationField;
    private TextArea descriptionField;
    private Button saveButton;
    private Button cancelButton;

    public AppointmentView() {
    }

    @Override
    public Stage getStage() {
        if(this.stage == null) {
            this.stage = new Stage();
            this.createStage();
        }

        return this.stage;
    }

    private void createStage(){
        this.nameField = new TextField();
        this.beginTimeHour = new Spinner<>(0, 23, 0, 1);
        this.beginTimeMinute = new Spinner<>(0, 59, 0, 1);
        this.endTimeHour = new Spinner<>(0, 23, 0, 1);
        this.endTimeMinute = new Spinner<>(0, 59, 0, 1);
        this.locationField = new TextField();
        this.descriptionField = new TextArea();
        this.saveButton = new Button("Opslaan");
        this.cancelButton = new Button("Annuleren");

        VBox vBox = new VBox();

        HBox buttonsBox = new HBox();
        buttonsBox.getChildren().add(this.cancelButton);
        buttonsBox.getChildren().add(this.saveButton);

        HBox beginTime = new HBox();
        beginTime.getChildren().add(this.beginTimeHour);
        beginTime.getChildren().add(this.beginTimeMinute);

        HBox endTime = new HBox();
        endTime.getChildren().add(this.endTimeHour);
        endTime.getChildren().add(this.endTimeMinute);

        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Naam: "), 0, 0);
        gridPane.add(new Label("Begintijd: "), 0, 1);
        gridPane.add(new Label("Eindtijd: "), 0, 2);
        gridPane.add(new Label("Locatie: "), 0, 3);
        gridPane.add(new Label("Omschrijving: "), 0, 4);
        gridPane.add(this.nameField, 1, 0);
        gridPane.add(beginTime, 1, 1);
        gridPane.add(endTime, 1, 2);
        gridPane.add(this.locationField, 1, 3);
        gridPane.add(this.descriptionField, 1, 4);

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        buttonsBox.setSpacing(10);
        buttonsBox.setAlignment(Pos.CENTER_RIGHT);

        vBox.getChildren().addAll(gridPane, buttonsBox);

        Scene scene = new Scene(vBox);
        this.stage.setScene(scene);
    }

    public TextField getNameField() {
        return nameField;
    }

    public Spinner<Integer> getBeginTimeHour() {
        return beginTimeHour;
    }

    public Spinner<Integer> getBeginTimeMinute() {
        return beginTimeMinute;
    }

    public Spinner<Integer> getEndTimeHour() {
        return endTimeHour;
    }

    public Spinner<Integer> getEndTimeMinute() {
        return endTimeMinute;
    }

    public TextField getLocationField() {
        return locationField;
    }

    public TextArea getDescriptionField() {
        return descriptionField;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }
}
