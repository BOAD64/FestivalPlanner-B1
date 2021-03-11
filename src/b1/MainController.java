package b1;

import b1.io.SchoolFile;
import b1.schedule.LessonController;
import b1.school.SchoolController;
import b1.school.group.GroupController;
import b1.school.person.*;
import b1.schedule.ScheduleController;
import b1.school.School;
import b1.school.room.ClassroomController;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainController implements Controller {

    private MainView view;
    private School school;
    private boolean showingSchedule = true;

    private ScheduleController scheduleController;
    private Node simulationNode;

    public MainController() {
        this.simulationNode = new Button("test");
    }

    @Override
    public void show() {
        show(null);
    }

    @Override
    public void show(Stage ownerStage) {
        this.view = new MainView();
        Stage stage = this.view.getStage();
        this.school = SchoolFile.getSchool();

        this.view.getGoToScheduleButton().setOnAction(e -> this.onGoToScheduleClick());
        this.view.getGoToSimulationButton().setOnAction(e -> this.onGoToSimulationClick());
        this.view.getSchoolEditButton().setOnAction(e -> this.onSchoolEditButtonClick());

        if (this.showingSchedule) {
            this.scheduleController = new ScheduleController();
            this.view.setScheduleControllerNode(this.scheduleController.getNode());
            this.fillAddMenuList(this.view.getAddList(), stage);
            this.view.getAddList().setOnMouseClicked(this::onAddListClicked);
        }
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(ownerStage);
        stage.show();
    }

    private void fillAddMenuList(ListView<AddMenuItem> addMenu, Stage stage) {
        addMenu.getItems().add(new AddMenuItem(GroupController.class, "Groep", stage));
        addMenu.getItems().add(new AddMenuItem(ClassroomController.class, "Klaslokaal", stage));
        addMenu.getItems().add(new AddMenuItem(StudentController.class, "Student", stage));
        addMenu.getItems().add(new AddMenuItem(TeacherController.class, "Docent", stage));
        addMenu.getItems().add(new AddMenuItem(LessonController.class, "Les", stage));

    }

    private void onAddListClicked(MouseEvent event) {
        AddMenuItem menuItem = this.view.getAddList().getSelectionModel().getSelectedItem();
        if (menuItem != null) {
            menuItem.onclick();
        }

        this.view.onAddListClicked(event);
    }

    private void onGoToScheduleClick() {
        this.showingSchedule = true;
        this.view.setScheduleControllerNode(this.scheduleController.getNode());
    }

    private void onGoToSimulationClick() {
        this.showingSchedule = false;
        this.view.setSimulationNode(this.simulationNode);
    }

    private void onSchoolEditButtonClick() {
        SchoolController schoolController = new SchoolController(SchoolFile.getSchool());
        schoolController.show(this.view.getStage());
    }

    public void onClose(EventHandler<WindowEvent> eventEventHandler) {
        this.view.getStage().setOnHidden(eventEventHandler);
    }
}
