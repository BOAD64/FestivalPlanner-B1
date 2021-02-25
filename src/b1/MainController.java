package b1;

import b1.school.School;

public class MainController implements Controller {

    private MainView view;
    private School school;

    public MainController(School school) {
        this.school = school;
    }

    @Override
    public void show() {
        this.view = new MainView(this.school);
        this.view.getStage().show();
    }
}
