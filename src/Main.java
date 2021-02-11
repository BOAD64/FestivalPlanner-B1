import static javafx.application.Application.launch;

public class Main {
    public static void main(String[] args) {
        System.out.println("development branch");
        //start program

        startGUI();
    }

    public static void startGUI(){
        launch(MainView.class);
    }
}
