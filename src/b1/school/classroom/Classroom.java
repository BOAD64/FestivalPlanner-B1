package b1.school.classroom;

import b1.school.person.Student;

import java.util.List;

public class Classroom {
    private String roomCode;
    private int capacity;

    public Classroom(String roomCode, int capacity) {
        this.roomCode = roomCode;
        this.capacity = capacity;
    }

    public String getRoomCode() {
        return this.roomCode;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
