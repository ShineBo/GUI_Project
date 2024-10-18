import java.util.Date;

public class Booking {
    private int bookingID;
    private Room room;
    private String guestName;
    private Date checkInDate;
    private Date checkOutDate;
    private boolean isBookingActive;

    public Booking(int bookingID, Room room, String guestName, Date checkInDate, Date checkOutDate) {
        this.bookingID = bookingID;
        this.room = room;
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.isBookingActive = false;
        room.setRoomAvailable(false);
    }

    // Getters and Setters
    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public boolean isBookingActive() {
        return isBookingActive;
    }

    public void setBookingActive(boolean isBookingActive) {
        this.isBookingActive = isBookingActive;
    }

    // Editing booking details
    public void editBooking(Date newCheckIn, Date newCheckOut) {
        this.checkInDate = newCheckIn;
        this.checkOutDate = newCheckOut;
    }

    // Canceling a booking
    public void cancelBooking() {
        this.isBookingActive = false;
        this.room.setRoomAvailable(true); // Make room available again
    }

    // Display booking info
    public void displayBookingInfo() {
        System.out.println("Booking ID: " + bookingID);
        System.out.println("Guest Name: " + guestName);
        System.out.println("Room: " + room.getRoomType() + " (Room Number: " + room.getRoomNumber() + ")");
        System.out.println("Check-in Date: " + checkInDate);
        System.out.println("Check-out Date: " + checkOutDate);
        System.out.println("Active: " + (isBookingActive ? "Yes" : "No"));
    }
}