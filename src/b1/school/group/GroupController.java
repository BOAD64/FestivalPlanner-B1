package b1.school.group;

import b1.Controller;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GroupController implements Controller {

    private Group group;
    private GroupView groupView;

    public GroupController()
    {
        this(new Group(""));
    }

    public GroupController(Group group) {
        this.group = group;
        this.groupView = new GroupView(group);
    }

    public GroupView getGroupView() {
        return groupView;
    }

    @Override
    public void show() {
        show(null);
    }

    @Override
    public void show(Stage ownerStage) {
        groupView.getStage().initModality(Modality.WINDOW_MODAL);
        groupView.getStage().initOwner(ownerStage);
        groupView.getStage().show();
    }
}
