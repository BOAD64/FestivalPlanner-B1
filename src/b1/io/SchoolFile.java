package b1.io;

import b1.schedule.Schedule;
import b1.school.School;

import java.io.*;

public class SchoolFile
{
    private static String filePath;
    private static School school;

    public static School getSchool() {
        if (school == null) {
            if(filePath == null || filePath.isEmpty()){
                return null;
            }
            school = readFile(filePath);
        }
        return school;
    }

    public static void save(){
        if(filePath == null || filePath.isEmpty()){
           return;
        }
        writeFile(filePath, school);
    }

    public static String getFilePath() {
        return filePath;
    }

    public static void setFilePath(String filePath) {
        SchoolFile.filePath = filePath;
    }

    public static void setSchool(School school) {
        SchoolFile.school = school;
    }

    private static School readFile(String fileName) {
        File file = new File(fileName);
        if(file.exists()) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
                return (School) objectInputStream.readObject();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    private static void writeFile(String filename, School school) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            objectOutputStream.writeObject(school);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
