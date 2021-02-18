package b1.school.classroom;

import b1.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.geom.Line2D;
import java.util.ArrayList;

public class ClassroomView implements View {


    private ArrayList<Classroom> classrooms;

    public ClassroomView() {
        this.classrooms = new ArrayList<>();
    }

    public void addClassrooms(ArrayList<Classroom> classrooms){
        this.classrooms = classrooms;
    }

    @Override
    public Stage getStage() {
        Stage stage = new Stage();
        BorderPane borderPane = new BorderPane();
        ComboBox<Classroom> comboBox = new ComboBox<>();
        for (Classroom c : this.classrooms){
            comboBox.getItems().add(c);
        }
        Label classroomCodeLabel = new Label("Lokaalcode:");
        Label classroomCapacityLabel = new Label("Capaciteit: ");
        TextField classroomCode = new TextField();
        TextField classroomCapacity = new TextField();
        comboBox.setOnAction(event -> {
            if (comboBox.getValue() != null){
                classroomCode.setText(comboBox.getValue().getRoomCode());
                String capacity = comboBox.getValue().getCapacity() + "";
                classroomCapacity.setText(capacity);
            }
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(classroomCodeLabel, classroomCode, classroomCapacityLabel, classroomCapacity);
        vBox.setSpacing(20);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(comboBox, vBox);
        hBox.setSpacing(20);


        borderPane.setTop(hBox);

        stage.setScene(new Scene(borderPane));
        stage.setMinWidth(500);
        stage.setMinHeight(500);
        stage.setMaxWidth(500);
        stage.setMaxHeight(500);
        return stage;
    }
}
