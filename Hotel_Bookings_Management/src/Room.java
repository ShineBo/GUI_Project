public class Room {
    int roomNumber;
    int roomFloor;
    String roomType;
    boolean isRoomAvailable;
    double roomPrice;

    public Room(int roomNumber, int roomFloor, String roomType, boolean isRoomAvailable, double roomPrice) {
        this.roomNumber = roomNumber;
        this.roomFloor = roomFloor;
        this.roomType = roomType;
        this.isRoomAvailable = isRoomAvailable;
        this.roomPrice = roomPrice;
    }

    // Getters and Setters
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomFloor() {
        return roomFloor;
    }

    public void setRoomFloor(int roomFloor) {
        this.roomFloor = roomFloor;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isRoomAvailable() {
        return isRoomAvailable;
    }

    public void setRoomAvailable(boolean isRoomAvailable) {
        this.isRoomAvailable = isRoomAvailable;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public void displayRoomInfo() {
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Room Floor: " + roomFloor);
        System.out.println("Room Type: " + roomType);
        System.out.println("Room Price: " + roomPrice);
        System.out.println("Available: " + (isRoomAvailable ? "Yes" : "No"));
    }
}

class StandardRoom extends Room {

    public StandardRoom(int roomNumber, int roomFloor, boolean isRoomAvailable, double roomPrice) {
        super(roomNumber, roomFloor, "Standard", isRoomAvailable, roomPrice);
    }

}

class DeluxeRoom extends Room {

    public DeluxeRoom(int roomNumber, int roomFloor, boolean isRoomAvailable, double roomPrice) {
        super(roomNumber, roomFloor, "Deluxe", isRoomAvailable, roomPrice);
    }

}