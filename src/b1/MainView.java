package b1;

import b1.io.ImageFile;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.FileInputStream;


public class MainView implements View {
    private Stage stage;
    private boolean hamburgerIsOut;
    private ListView<AddMenuItem> addList;
    private ImageView plusImageView;

    private HBox HBox;
    private VBox optionMenuVBox;
    private StackPane stackPane;
    private Node scheduleControllerNode;
    private Node simulationNode;

    private Button goToScheduleButton;
    private Button goToSimulationButton;
    private Button schoolEditButton;

//    enum Controllers{GROUP,CLASSROOM, STUDENT, TEACHER, APPOINTMENT}

    public MainView() {
    }

    @Override
    public Stage getStage() {
        if(this.stage == null) {
            createStage();
        }
        return this.stage;
    }

    public ListView<AddMenuItem> getAddList() {
        return this.addList;
    }

    public Button getSchoolEditButton() {
        return this.schoolEditButton;
    }

    void setSimulationNode(Node simulationNode) {
        if(this.HBox.getChildren().contains(simulationNode)) {
            return;
        }

        if(simulationNode == null) {
            return;
        }

        this.simulationNode = simulationNode;
        if(this.scheduleControllerNode != null) {
            this.HBox.getChildren().remove(this.stackPane);
        }

        this.HBox.getChildren().add(simulationNode);
    }

    void setScheduleControllerNode(Node scheduleControllerNode) {
        if(this.HBox.getChildren().contains(this.stackPane)) {
            return;
        }
        if(this.simulationNode != null) {
            this.HBox.getChildren().remove(this.simulationNode);
        }

        this.scheduleControllerNode = scheduleControllerNode;
        if(this.stackPane == null) {
            this.stackPane = new StackPane();
        } else {
            this.stackPane.getChildren().clear();
        }

        ((Canvas) scheduleControllerNode).setWidth(Double.MAX_VALUE);
        ((Canvas) scheduleControllerNode).setHeight(Double.MAX_VALUE);
        this.stackPane.getChildren().addAll(scheduleControllerNode, this.plusImageView, this.addList);
        StackPane.setAlignment(this.plusImageView, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(this.addList, Pos.BOTTOM_RIGHT);
        this.addList.setTranslateY(-125);
        this.plusImageView.setTranslateX(-20);
        this.plusImageView.setTranslateY(-10);

        this.HBox.getChildren().add(this.stackPane);
    }

    public void onAddListClicked(MouseEvent event) {
        this.changeVisibilityOfAddList();
    }

    private void createStage() {
        this.stage = new Stage();
        this.HBox = new HBox();
        FileInputStream plusInputStream = null;
        FileInputStream arrowInputStream = null;
        try {
            plusInputStream = new FileInputStream("resources\\plus.png");
            arrowInputStream = new FileInputStream("resources\\arrow.png");
        } catch(Exception e) {
            e.printStackTrace();
        }
        if(plusInputStream != null && arrowInputStream != null) {
            Image plus = new Image(plusInputStream);
            Image arrow = new Image(arrowInputStream);
            this.plusImageView = new ImageView(plus);
            ImageView arrowImageView = new ImageView(arrow);
            this.plusImageView.setScaleX(Math.PI * 2 / 10.0f); //wtf
            this.plusImageView.setScaleY(Math.PI * 2 / 10.0f);

            arrowImageView.setScaleX(Math.PI * 2 / 10.0f); //wtf
            arrowImageView.setScaleY(Math.PI * 2 / 10.0f);

            this.addList = new ListView<>();
            this.addList.setMaxHeight(200);
            this.addList.setMaxWidth(150);
            this.addList.getItems().addListener(this::onListViewItemsChanged);
            this.addList.setVisible(false);
            this.hamburgerIsOut = false;

            this.plusImageView.setOnMouseClicked(event -> this.changeVisibilityOfAddList());

            //create HBox used for whole hamburger-menu
            HBox hamburger = new HBox();
            hamburger.getChildren().add(arrowImageView);

            //test
            this.optionMenuVBox = new VBox();
            this.optionMenuVBox.setMinWidth(150);
            this.optionMenuVBox.setAlignment(Pos.TOP_CENTER);

            //comboBoxes.setBackground(new Background(new BackgroundFill(Color.hsb(0, 0, 0.255), CornerRadii.EMPTY, Insets.EMPTY)));
            this.optionMenuVBox.setBackground(new Background(new BackgroundFill(Color.rgb(65, 65, 65), CornerRadii.EMPTY, Insets.EMPTY)));
            this.HBox.setBackground(new Background(new BackgroundFill(Color.rgb(65, 65, 65), CornerRadii.EMPTY, Insets.EMPTY)));

            //Opening and closing of hamburger menu
            arrowImageView.setOnMouseClicked(event -> {

                if(this.hamburgerIsOut) {
                    this.hamburgerIsOut = false;
                    hamburger.getChildren().remove(0, 2);
                    arrowImageView.setRotate(0);
                    hamburger.getChildren().addAll(arrowImageView);
                } else {
                    this.hamburgerIsOut = true;
                    hamburger.getChildren().remove(0, 1);
                    arrowImageView.setRotate(180);
                    hamburger.getChildren().addAll(optionMenuVBox, arrowImageView);

                }
            });

            VBox addMenu = new VBox();
            addMenu.getChildren().addAll(this.addList, this.plusImageView);
            addMenu.setSpacing(20);
            addMenu.setAlignment(Pos.BOTTOM_RIGHT);


            this.HBox.getChildren().add(hamburger);

            this.stage.setScene(new Scene(this.HBox));
            this.stage.setWidth(1200);
            this.stage.setHeight(1000);
            this.stage.setTitle("Hogwarts Simulator");
        }

        this.initButtons();
        this.stage.getIcons().add(ImageFile.getLogo());

    }

    private void initButtons() {
        this.goToScheduleButton = new Button("Rooster");
        this.goToSimulationButton = new Button("Simulatie");
        this.schoolEditButton = new Button("Edit School");

        this.optionMenuVBox.getChildren().addAll(this.goToScheduleButton, this.goToSimulationButton, this.schoolEditButton);
    }

    private void changeVisibilityOfAddList() {
        this.addList.setVisible(!this.addList.isVisible());
    }

    Button getGoToScheduleButton() {
        return this.goToScheduleButton;
    }

    Button getGoToSimulationButton() {
        return this.goToSimulationButton;
    }

    private void onListViewItemsChanged(ListChangeListener.Change change) {
        this.addList.setMaxHeight(this.addList.getItems().size() * 25);
    }
}