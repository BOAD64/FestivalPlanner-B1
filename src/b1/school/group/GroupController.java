package b1.school.group;

import b1.Controller;

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
        groupView.getStage().show();
    }
}
