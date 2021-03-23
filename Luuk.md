# Luuk Verhagen (2170208)
# Inhoudsopgave
- [Reflectie](#reflectie)
  * [Week 3](#week-3)
  * [Week 4](#week-4)
  * [Week 5](#week-5)
# Reflectie
## Week 3
Deze week heb ik in de eerste plaats de appointmentview afgemaakt. Met deze appointmentview kun je lessen en andere afspraken in het systeem aanpassen of creëren. In het systeem hebben we onderscheid gemaakt tussen twee type appointments: lessen en normale afspraken. Om dit te realiseren hadden we tijdens het het ontwerpen twee klassen bedacht: appointment en lessons die overerfde van appointment. Bij het realiseren van deze klassen kwamen we er achter dat dit niet praktisch was. Qua data klopte die gedachten ook, maar qua view niet. In de lessonview kon je personen koppelen aan een les door een groep toe te voegen aan die les, waar de personen dan weer in zitten. Dit was bij de generalappointmentview niet het geval, hier moest je personen handmatig een voor een toevoegen. Achteraf gezien hadden we de oude overervings structuur wel kunnen behouden, maar de views niet moeten laten overerven. 

De eerste structuur die we hadden gekozen om de view overerving te doen was de volgende methode:

```java
public abstract class AppointmentViewAbstract implements View {

    //Allerlei fields

    public AppointmentViewAbstract() {
    }

    //Al onze view hebben een createStage methode
    private void createStage(){
        //Elementen

        this.childStage(pane);

        //Nog meer elementen
    }

    public abstract void childStage(Pane pane);

    ...
}
```

```java
public class LessonView extends AppointmentViewAbstract{

    public LessonView() {
        super();
    }

    public override childStage(Pane pane)
    {
        //Creatie van allerlei elementen.
    }
}
```

Dit vonden wij niet fijn werken aangezien je in je lessonview weinig vrijheid had. Dus zijn we voor de volgende methode gegaan, waarbij we in de lessonview de creatie methode van de parent aanroepen en vervolgens toevoegen aan de stage. Hierbij erven we de create stage methode niet over. Op deze manier hadden we in de child views van AppointmentViewAbstract meer vrijheid om per view het anders aan te pakken.

```Java
public abstract class AppointmentViewAbstract implements View {

    public AppointmentViewAbstract() {
    }

    GridPane createAbstractAppointmentElements() {
        Pane pane = new Pane();

        //allerlei elementen

        return pane;
    }
}
```
```Java
public class LessonView extends AppointmentViewAbstract{

    public LessonView() {
        super();
    }

    public Stage getStage() {
        if(this.stage == null) {
            this.stage = new Stage();
            this.createStage();
        }
        return this.stage;
    }

    private void createStage(){
        Pane pane = new Pane();

        //creatie van allerlei elementen

        pane.getChildren().add(super.createAbstractAppointmentElements());

        //creatie van allerlei elementen

        Scene scene = new Scene(pane);
        this.stage.setScene(scene);
        this.stage.getIcons().add(ImageFile.getLogo());
        this.stage.setTitle("Les");
    }
}
```
Daarnaast heb ik in deze week de SchoolFile klas gemaakt. Deze klas is verantwoordelijk voor het inladen en opslaan van alle data. Daarnaast zorgt deze klas ervoor dat het alleen bij de eerste aanroep wordt ingeladen en daarna gecached wordt. 
Daarnaast heb ik in deze week gewerkt aan het tonen van de schedule.


## Week 4
Week 4 was voor mij een week van refactoren samen met Jeroen. Afgelopen donderdag hadden we alle branches samengevoegd. Hierbij kwamen we er achter dat we 3 verschillende code stylen hadden (3 duo's). Hierdoor ging het samenvoegen van de code niet soepel. Om dit toch soepel te laten verlopen, moest er redelijk wat gerefactored worden. Bovendien kwamen we tijdens het testen heel veel kleine fouten tegen. Deze hebben we opgelost. Iets wat we veel zagen was dat events in de view werden afgehandeld, terwijl het de bedoeling was dat dit in de conroller gebeurde, dit hebben we veranderd. Iets waar ik aan gewerkt heb is de verandering van het 'add' menu. Het menu waarin je kon kiezen om een element toe te voegen. Hierbij werd gebruik gemaakt van een listview. Om de selectie af te handelen werd gebruik gemaakt van een lange switch. 

```java
private void onAddListClicked(MouseEvent event) {
    MainView.Controllers controller = this.view.getAddList().getSelectionModel().getSelectedItem();

    switch (controller) {
        case GROUP:
            Group group = new Group("unnamed");
            this.school.addGroup(group);
            GroupController groupController = new GroupController(group);
            groupController.show();
            break;
        case CLASSROOM:
            Classroom classroom = new Classroom(0, 0, "unnamed", 0);
            this.school.addRoom(classroom);
            ClassroomController classroomController = new ClassroomController(classroom);
            classroomController.show();
            break;
        case STUDENT:
            Student student = new Student();
            this.school.addStudent(student);
            StudentController studentController = new StudentController(student);
            studentController.show();
            break;
        case TEACHER:
            Teacher teacher = new Teacher();
            this.school.addTeacher(teacher);
            TeacherController teacherController = new TeacherController(teacher);
            teacherController.show();
            break;
        case APPOINTMENT: {
            Lesson lesson = new Lesson(null, null, null, null, null, null, null);
            this.school.getSchedule().getAppointments().add(lesson);
            LessonController lessonController = new LessonController(this.school, lesson);
            lessonController.onClose(event1 -> {this.scheduleController.refresh();});
            lessonController.show();
            break;
        }
    }
}
```

Ik heb een apparte AddMenuItem class aangemaakt die dan toegevoegd werd aan het addmenu. Bij het klikken op een item hoefde ik alleen nog maar de click functie uit te voeren.

De AddMenuItem klas:
```java
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
```

Waarbij de maincontroller er zo uitzag.
```java
//Imports

public class MainController implements Controller
{

    private MainView view;
    private School school;

    private ScheduleController scheduleController;

    public MainController() {
    }

    @Override
    public void show() {
        this.view = new MainView();
        Stage stage = this.view.getStage();
        this.school = SchoolFile.getSchool();

        this.scheduleController = new ScheduleController();
        this.view.setScheduleControllerNode(this.scheduleController.getNode());
        this.fillAddMenuList(this.view.getAddList());

        this.view.getAddList().setOnMouseClicked(this::onAddListClicked);
        stage.show();
    }

    private void fillAddMenuList(ListView<AddMenuItem> addMenu) {
        addMenu.getItems().add(new AddMenuItem(GroupController.class, "Groep"));
        addMenu.getItems().add(new AddMenuItem(ClassroomController.class, "Klaslokaal"));
        addMenu.getItems().add(new AddMenuItem(StudentController.class, "Student"));
        addMenu.getItems().add(new AddMenuItem(TeacherController.class, "Docent"));
        addMenu.getItems().add(new AddMenuItem(LessonController.class, "Les"));

    }

    private void onAddListClicked(MouseEvent event) {
        AddMenuItem menuItem = this.view.getAddList().getSelectionModel().getSelectedItem();
        menuItem.onclick();

        this.view.onAddListClicked(event);
    }
}
```

Deze manier van werken is naar mijn mening veel netter en overzichtelijker. Bovendien is deze methode ook veel schaalbaarder.


## Week 5
In week 5 heb ik dinsdag de laatste hand gelegd aan de roostermodule door alle bugs eruit te halen en de laatste dingen aan elkaar te koppelen. 
Donderdag zijn we begonnen aan het simulatie gedeelte van de opdracht. Eerst hebben we met z'n allen een begin ontwerp gemaakt van de de simulatiemodule. Deze zag er zo uit:

![alt text][logo]

[logo]: Klassendiagram%20simulator.png "Klassen diagram"

Ik heb deze week samen gewerkt aan de map, layer, io en simulation klassen. Dit bevatte niet echt veel uitdagingen, aangezien Johan dit al redelijk voorgekauwd had. 

Het enige probleem die we tegen kwamen, was het feit dat `Setting.class.getResource(path).getPath()` spaties verving door de %20 equivalent. De File klas daarentegen kon daar niet goed mee omgaan en dus kon het bestand niet worden gevonden. Het duurde even voordat we daar achter kwamen. De oplossing was simpel door een replace toe te passen op de string.
Later zijn we direct gebruik gaan maken van de url klas en niet via een path string op advies van Johan. Daarnaast had Johan ons geadviseerd om layers te cachen in een bufferde image voor optimalisatie.


## Week 6
In het begin van week 6 heb ik gewerkt aan de camera klas en heb we de map layer structuur omgegooid. De camera klas hadden we snel voorelkaar aangezien we die konden hergebruiken van eerder gemaakten opdrachten van 2D Computer Graphics. Op het gebied van de map hebben gebruik gemaakt van de layer klas en hebben we een tile klas toegevoegd. Waar eerst de map klas voor alle layers bijhield welke tile op welke positie zat, hoefde hij nu alleen de layers in te laden en bij te houden. De layer klas is verantwoordelijk voor het tekenen van zijn eigen layer, waarbij er nu op layer niveau wordt gecached. Daarnaast zijn we gebruik gaan maken van tile objecten in plaats van een dubbele x y array. Op deze manier hoeft de layer klas zich alleen bezich te houden met de gevulde tiles en niet met de tiles die leeg zijn.

Aan het einde van de week ben ik samen met andere begonnen aan de pathfinding. Het programmeren van de pathfinding klas ging vrij soepel in het begin. Johan had de structuur al vrij goed voorgekouwd, waardoor we het alleen nog hoefde uit te werken. Wel zorgde een opstapeling van aardig wat kleine foutjes ervoor dat het even duurde voordat het uiteindelijk werkend was. Zo waren we vergeten rekening te houden met het feit dat we bij het aanmaken van tile objecten de index hadden omgerekend naar zero bases. Wat vrij lang problemen gaf. Daarnaast hadden we ergens een vector waar de de y en x hadden omgedraaid, wat ook weer problemen gaf. Uiteinddelijk is de pathfinding klas goed gelukt.