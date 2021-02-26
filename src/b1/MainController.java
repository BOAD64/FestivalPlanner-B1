package b1;

import b1.school.School;

import java.util.ArrayList;

public class MainController implements Controller {

    private MainView view;
    private School school;
    private ArrayList<School> schools;

    public MainController(ArrayList<School> schools) {
        this.schools = schools;
    }

    @Override
    public void show() {
        this.view = new MainView(this.schools);
        this.view.getStage().show();
    }
}
