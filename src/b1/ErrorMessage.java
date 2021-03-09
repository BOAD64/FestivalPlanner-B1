package b1;

import javafx.scene.control.Alert;
import sun.audio.AudioPlayer;

import java.awt.*;

public class ErrorMessage
{
    public static void show()
    {
        show("Er is een fout bij het opslaan, check of u overal valide waarden heeft ingevuld!");
    }

    public static void show(String contextText)
    {
        show("Er is iets fout gegaan", contextText);
    }

    public static void show(String headerText, String contentText) {
        show("Error", headerText, contentText);
    }

    public static void show(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        Toolkit.getDefaultToolkit().beep();
        alert.showAndWait();
    }
}
