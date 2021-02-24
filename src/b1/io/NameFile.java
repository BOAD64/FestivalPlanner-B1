package b1.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class NameFile {

    private static ArrayList<String> names = new ArrayList<>();

    /**
     * This method reads the file names.txt and puts every line into an ArrayList.
     * @return ArrayList with al the names read from the file
     */
    public static ArrayList<String> readFile() {
        File file = new File("resources/names.txt");

        try(Scanner scanner = new Scanner(file)) {
            while(scanner.hasNext()) {
                names.add(scanner.nextLine());
            }

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        return names;
    }
}
