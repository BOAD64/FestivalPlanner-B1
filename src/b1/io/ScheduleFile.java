package b1.io;

import b1.schedule.Schedule;

import java.io.*;

public class ScheduleFile {

    private static String filePath;
    private static Schedule schedule;

    public static Schedule getSchedule() {
        if (schedule == null) {
            if(filePath.isEmpty()){
                return null;
            }
            schedule = readFile(filePath);
        }
        return schedule;
    }

    public static void save(){
        if(filePath == null || filePath.isEmpty()){
           return;
        }
        writeFile(filePath, schedule);
    }

    public static String getFilePath() {
        return filePath;
    }

    public static void setFilePath(String filePath) {
        ScheduleFile.filePath = filePath;
    }

    public static void setSchedule(Schedule schedule) {
        ScheduleFile.schedule = schedule;
    }

    private static Schedule readFile(String fileName) {
        File file = new File(fileName);
        if(!file.exists()) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
                return (Schedule) objectInputStream.readObject();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    private static void writeFile(String filename, Schedule schedule) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            objectOutputStream.writeObject(schedule);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
