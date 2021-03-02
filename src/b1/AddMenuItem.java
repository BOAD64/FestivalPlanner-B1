package b1;

import b1.school.person.Student;
import b1.school.person.StudentController;

import javax.naming.ldap.Control;
import java.lang.reflect.Constructor;

public class AddMenuItem
{
    private Class<? extends Controller> controller;
    private String title;

    public AddMenuItem(Class<? extends Controller> controller, String title) {
        this.controller = controller;
        this.title = title;
    }

    public Class<? extends Controller> getController() {
        return controller;
    }

    public void setController(Class<? extends Controller> controller) {
        this.controller = controller;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void onclick() {
        try {
            Controller controller = this.controller.getConstructor().newInstance();
            controller.show();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.title;
    }
}
