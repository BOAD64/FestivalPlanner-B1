package b1.school.room;


public class Classroom extends Room {
    private String roomCode;
    private int capacity;

    public Classroom(double width, double length, String roomCode, int capacity) {
        super(width, length);
        this.roomCode = roomCode;
        this.capacity = capacity;
    }

    public String getRoomCode() {
        return this.roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Classroom)) {
            return false;
        }
        Classroom toCheck = (Classroom) obj;
        return toCheck.getRoomCode().equals(this.roomCode);
    }

    @Override
    public String toString() {
        return this.roomCode;
    }
}
