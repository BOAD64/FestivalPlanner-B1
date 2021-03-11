package b1.school.person;

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
}
