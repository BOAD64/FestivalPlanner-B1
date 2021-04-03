package b1.school.room;

public class Classroom extends Room {
    private int capacity;

    public Classroom(double width, double length, String roomCode, int capacity) {
        super(roomCode, width, length);
        this.capacity = capacity;
    }

    public String getRoomCode() {
        return super.getName();
    }

    public void setRoomCode(String roomCode) {
        super.setName(roomCode);
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
        return toCheck.getRoomCode().equals(this.getName());
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
