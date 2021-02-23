package b1.school.person;

import b1.io.NameFile;

import java.util.ArrayList;

public class StudentGenerator {

    private ArrayList<String> names;
    private String[] firstName = new String[]{"Tinke", "Neele", "Dora", "Frenk", "Kiran", "Jeff", "Johan", "Edwin",
    "Liedeke", "Valérie"};
    private String[] lastName = new String[]{"Remmers", "Bink", "Cleven", "van Olffen", "de Deugd", "Somai", "Talboom",
    "van der Geld", "Vegter", "Vloet"};

    public StudentGenerator(short amount) {
        this.names = NameFile.readFile();

        for(int i = this.names.size(); i < amount; i++) {
            this.addName();
        }

        while(amount < this.names.size()) {
            this.removeName();
        }
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


    public ArrayList<String> getNames() {
        return names;
    }
}
