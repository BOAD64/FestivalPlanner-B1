# Luuk Verhagen (2170208)
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