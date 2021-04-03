package b1.school.person;

import b1.io.NameFile;
import b1.io.SchoolFile;
import b1.school.group.Group;

import java.util.ArrayList;

public class StudentGenerator {

    private ArrayList<String> names;
    private ArrayList<Group> groups = new ArrayList<>();
    private String[] genders = new String[]{"Man", "Vrouw"};
    private String[] firstName = new String[]{"Tinke", "Neele", "Dora", "Frenk", "Kiran", "Jeff", "Johan", "Edwin", "Liedeke", "Valérie"};
    private String[] lastName = new String[]{"Remmers", "Bink", "Cleven", "van Olffen", "de Deugd", "Somai", "Talboom", "van der Geld", "Vegter", "Vloet"};

    /**
     * This method generates an ArrayList with random names from the names.txt file. If the file does not contain enough
     * names or could not be read at all, there will be names generated out of the preset String[]
     * firstName and lastName.
     *
     * @param amount is the amount of names that are put into the ArrayList with random names.
     */
    public StudentGenerator(short amount) {
        if (amount > 600) {
            throw new IllegalArgumentException();
        }
        this.names = NameFile.readFile();
        for (int i = 0; i < amount; i += 50) {
            Group group = new Group("Groep " + i / 50);
            groups.add(group);
            SchoolFile.getSchool().getGroups().add(group);
        }

        for (int i = this.names.size(); i < amount; i++) {
            this.addName();
        }

        while (amount < this.names.size()) {
            this.removeName();
        }

        this.addStudents();
    }

    private void addName() {
        boolean running = true;
        while (running) {
            String name = this.firstName[(int) (Math.random() * 10)] + " " + this.lastName[(int) (Math.random() * 10)];

            if (!this.names.contains(name)) {
                this.names.add(name);
                running = false;
            }
        }
    }

    private void removeName() {
        this.names.remove((int) (Math.random() * this.names.size()));
    }

    private void addStudents() {
        for (String name : this.names) {
            short age = (short) (Math.random() * 20 + 10);
            String gender = this.genders[(int) (Math.random() * this.genders.length)];
            short id = 0;

            Group selectedGroup = this.groups.get((int) (Math.random() * this.groups.size()));
            Student student = new Student(name, age, gender, id, selectedGroup);
            SchoolFile.getSchool().addStudent(student);
            SchoolFile.getSchool().getGroups().get(SchoolFile.getSchool().getGroups().indexOf(selectedGroup)).
                    addStudent(student);
        }
    }

    public ArrayList<String> getNames() {
        return this.names;
    }
}
