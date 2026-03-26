import java.util.*;

/**
 * Use Case 10 - Booking Cancellation & Inventory Rollback
 */

// Reservation class
class Reservation {
    String reservationId;
    String roomType;

    public Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }
}

// Inventory class
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single", 1);
        inventory.put("Double", 1);
        inventory.put("Suite", 1);
    }

    public void increase(String roomType) {
        inventory.put(roomType, inventory.getOrDefault(roomType, 0) + 1);
    }

    public void decrease(String roomType) {
        inventory.put(roomType, inventory.getOrDefault(roomType, 0) - 1);
    }

    public void display() {
        System.out.println("Inventory: " + inventory);
    }
}

// Cancellation Service
class CancellationService {

    private Map<String, Reservation> bookings = new HashMap<>();
    private Stack<String> rollbackStack = new Stack<>();
    private RoomInventory inventory;

    public CancellationService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    // Add booking (simulate confirmed booking)
    public void addBooking(Reservation r) {
        bookings.put(r.reservationId, r);
        inventory.decrease(r.roomType);
    }

    // Cancel booking
    public void cancelBooking(String reservationId) {

        if (!bookings.containsKey(reservationId)) {
            System.out.println("Invalid cancellation. Reservation not found.");
            return;
        }

        Reservation r = bookings.remove(reservationId);

        // Push to rollback stack
        rollbackStack.push(reservationId);

        // Restore inventory
        inventory.increase(r.roomType);

        System.out.println("Cancelled reservation: " + reservationId);
    }

    public void showRollbackStack() {
        System.out.println("Rollback Stack: " + rollbackStack);
    }
}

// Main Class
public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService(inventory);

        // Simulate bookings
        service.addBooking(new Reservation("RES101", "Single"));
        service.addBooking(new Reservation("RES102", "Double"));

        inventory.display();

        // Cancel booking
        service.cancelBooking("RES101");

        inventory.display();

        // Invalid cancel
        service.cancelBooking("RES999");

        service.showRollbackStack();
    }
}