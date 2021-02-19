package b1.school.group;

import b1.Controller;
import b1.school.classroom.ClassroomView;

public class GroupController implements Controller {
    private Group group;
    private GroupView groupView;

    public GroupController(Group group) {
        this.group = group;
        this.groupView = new GroupView(group);
    }

    public GroupView getGroupView() {
        return groupView;
    }

    @Override
    public void show() {
        groupView.getStage().show();
    }
}
