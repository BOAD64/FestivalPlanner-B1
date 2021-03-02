package b1;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
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
    ListView<AddMenuItem> addList;
    private ImageView plusImageView;

    private HBox HBox;
    private StackPane stackPane;
    private Node scheduleControllerNode;

//    enum Controllers{GROUP,CLASSROOM, STUDENT, TEACHER, APPOINTMENT}

    public MainView() {
    }

    @Override
    public Stage getStage() {

        createStage();
        return this.stage;
    }

    public ListView<AddMenuItem> getAddList() {
        return addList;
    }

    public void setScheduleControllerNode(Node scheduleControllerNode) {
        this.scheduleControllerNode = scheduleControllerNode;

        if(this.stackPane == null) {
            stackPane = new StackPane();
            this.HBox.getChildren().add(stackPane);
        } else {
            stackPane.getChildren().clear();
        }

        stackPane.getChildren().addAll(this.scheduleControllerNode, plusImageView, this.addList);
        stackPane.setAlignment(plusImageView, Pos.BOTTOM_RIGHT);
        stackPane.setAlignment(this.addList, Pos.BOTTOM_RIGHT);
        this.addList.setTranslateY(-125);
        this.plusImageView.setTranslateX(-20);
        this.plusImageView.setTranslateY(-10);
    }

    public void onAddListClicked(MouseEvent event) {
        this.changeVisibilityOfAddList();
    }

    private void createStage() {
        this.stage = new Stage();
        this.HBox = new HBox();
        FileInputStream plusInputStream = null;
        FileInputStream arrowInputStream = null;
        FileInputStream logoInputStream = null;
        try {
            plusInputStream = new FileInputStream("resources\\plus.png");
            arrowInputStream = new FileInputStream("resources\\arrow.png");
            logoInputStream = new FileInputStream("resources\\logo.png");


            Image image = new Image(logoInputStream);
            this.stage.getIcons().add(image);
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
            this.addList.setVisible(false);
            this.hamburgerIsOut = false;

            plusImageView.setOnMouseClicked(event -> {
                this.changeVisibilityOfAddList();
            });

            this.addList.setOnMouseClicked(event -> {

            });

            //create HBox used for whole hamburger-menu
            HBox hamburger = new HBox();
            hamburger.getChildren().add(arrowImageView);

            //test
            VBox comboBoxes = new VBox();
            comboBoxes.setMinWidth(150);
            comboBoxes.setAlignment(Pos.TOP_CENTER);


            //comboBoxes.setBackground(new Background(new BackgroundFill(Color.hsb(0, 0, 0.255), CornerRadii.EMPTY, Insets.EMPTY)));
            comboBoxes.setBackground(new Background(new BackgroundFill(Color.rgb(65, 65, 65), CornerRadii.EMPTY, Insets.EMPTY)));
            HBox.setBackground(new Background(new BackgroundFill(Color.rgb(65, 65, 65), CornerRadii.EMPTY, Insets.EMPTY)));

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
                    hamburger.getChildren().addAll(comboBoxes, arrowImageView);

                }
            });

            VBox addMenu = new VBox();
            addMenu.getChildren().addAll(addList, plusImageView);
            addMenu.setSpacing(20);
            addMenu.setAlignment(Pos.BOTTOM_RIGHT);


            this.HBox.getChildren().add(hamburger);

            this.stage.setScene(new Scene(HBox));
            this.stage.setWidth(1200);
            this.stage.setHeight(1000);
            this.stage.setTitle("Hogwarts Simulator");
        }


    }

    private void changeVisibilityOfAddList() {
        this.addList.setVisible(!this.addList.isVisible());
    }
}