package b1.school.person;

import b1.io.NameFile;

import java.util.ArrayList;

public class StudentGenerator {

    private ArrayList<String> names;
    private String[] genders = new String[]{"Man", "Vrouw"};
    private String[] firstName = new String[]{"Tinke", "Neele", "Dora", "Frenk", "Kiran", "Jeff", "Johan", "Edwin",
    "Liedeke", "Valérie"};
    private String[] lastName = new String[]{"Remmers", "Bink", "Cleven", "van Olffen", "de Deugd", "Somai", "Talboom",
    "van der Geld", "Vegter", "Vloet"};

    /**
     * This method generates an ArrayList with random names from the names.txt file. If the file does not contain enough
     * names or could not be read at all, there will be names generated out of the preset String[]
     * firstName and lastName.
     * @param amount is the amount of names that are put into the ArrayList with random names.
     */
    public StudentGenerator(short amount) {
        this.names = NameFile.readFile();

        for(int i = this.names.size(); i < amount; i++) {
            this.addName();
        }

        while(amount < this.names.size()) {
            this.removeName();
        }

        this.addStudents();
    }

    private void addName() {
        boolean running = true;
        while(running) {
            String name = this.firstName[(int) (Math.random() * 10)] + " " + this.lastName[(int) (Math.random() * 10)];

            if(!this.names.contains(name)) {
                this.names.add(name);
                running = false;
            }
        }
    }

    private void removeName() {
        this.names.remove((int)(Math.random() * this.names.size()));
    }

    private void addStudents() {
        for(String name : this.names) {
            short age = (short)(Math.random() * 20 + 10);
            String gender = this.genders[(int)(Math.random() * this.genders.length)];
            short id = 0; //ToDo request new id from school
            String group = ""; //ToDo request group list

            Student student = new Student(name, age, gender, id, group);
            //ToDo add student to school
        }
    }

    public ArrayList<String> getNames() {
        return names;
    }
}
