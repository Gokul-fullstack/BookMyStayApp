import java.util.*;

/**
 * Use Case 9 - Error Handling & Validation
 */

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Inventory Class
class RoomInventory {
    private Map<String, Integer> rooms = new HashMap<>();

    public RoomInventory() {
        rooms.put("Single", 2);
        rooms.put("Double", 1);
        rooms.put("Suite", 0);
    }

    public void bookRoom(String roomType) throws InvalidBookingException {

        // Validation 1: Check room type
        if (!rooms.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        int available = rooms.get(roomType);

        // Validation 2: Check availability
        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for: " + roomType);
        }

        // Booking success → reduce count
        rooms.put(roomType, available - 1);

        System.out.println("Booking confirmed for " + roomType + " room.");
    }

    public void displayInventory() {
        System.out.println("Current Inventory: " + rooms);
    }
}

// Main
public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        // Test inputs (valid + invalid)
        String[] requests = {"Single", "Suite", "Luxury", "Double"};

        for (String room : requests) {
            try {
                inventory.bookRoom(room);
            } catch (InvalidBookingException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        inventory.displayInventory();
    }
}