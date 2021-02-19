package b1.school;

import b1.Controller;

public class SchoolController implements Controller {

    private School school;
    private SchoolView schoolView;

    public SchoolController(School school) {
        this.school = school;
        this.schoolView = new SchoolView(this.school);
    }

    @Override
    public void show() {
        this.schoolView.getStage().show();
    }
}
