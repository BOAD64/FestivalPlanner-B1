package b1.school.person;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public abstract class PersonController {

    /**
     * This method checks if the values given by the user to add a person are valid.
     *
     * @param view is the PersonView where the input fields are located
     * @return true if the inputs are valid
     */
    boolean personIsValid(PersonView view) {
        try {
            return !(view.getNameField().getText().isEmpty() || view.getAgeField().getText().isEmpty() ||
                    view.getGenderField().getText().isEmpty()  || Integer.parseInt(view.getAgeField().getText()) < 1);

        } catch(Exception e) {
            return false;
        }
    }

    /**
     * This method shows an error massage that displays that something went wrong while saving. It prompts the user
     * to check if the inputs given are valid values.
     */
    void showErrorMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Er is iets fout gegaan");
        alert.setContentText("Er is een fout bij het opslaan, check of u overal valide waarde heeft ingevuld!");
        alert.showAndWait();
    }
}
