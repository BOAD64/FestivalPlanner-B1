package b1.school.person;

import javafx.scene.control.Alert;

public abstract class PersonController {

    boolean personIsValid(PersonView view) {
        try {
            return !(view.getNameField().getText().isEmpty() || view.getAgeField().getText().isEmpty() ||
                    view.getGenderField().getText().isEmpty()  || Integer.parseInt(view.getAgeField().getText()) < 1);

        } catch(Exception e) {
            return false;
        }
    }

    void showErrorMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Er is iets fout gegaan");
        alert.setContentText("Er is een fout bij het opslaan, check of u valide waarde heeft ingevuld!");
        alert.showAndWait();
    }
}
