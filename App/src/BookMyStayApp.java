import java.util.*;

/**
 * Use Case 11 - Concurrent Booking Simulation
 */

// Reservation request
class BookingRequest {
    String reservationId;
    String roomType;

    public BookingRequest(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }
}

// Thread-safe Inventory
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single", 2);
        inventory.put("Double", 1);
    }

    // synchronized method (critical section)
    public synchronized boolean bookRoom(String roomType, String reservationId) {

        int available = inventory.getOrDefault(roomType, 0);

        if (available > 0) {
            inventory.put(roomType, available - 1);
            System.out.println(Thread.currentThread().getName() +
                    " booked " + roomType + " for " + reservationId);
            return true;
        } else {
            System.out.println(Thread.currentThread().getName() +
                    " FAILED booking for " + reservationId + " (No rooms)");
            return false;
        }
    }

    public void display() {
        System.out.println("Final Inventory: " + inventory);
    }
}

// Worker Thread
class BookingProcessor extends Thread {

    private Queue<BookingRequest> queue;
    private RoomInventory inventory;

    public BookingProcessor(Queue<BookingRequest> queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    @Override
    public void run() {
        while (true) {
            BookingRequest request;

            // synchronized access to shared queue
            synchronized (queue) {
                if (queue.isEmpty()) break;
                request = queue.poll();
            }

            if (request != null) {
                inventory.bookRoom(request.roomType, request.reservationId);
            }
        }
    }
}

// Main class
public class BookMyStayApp {

    public static void main(String[] args) {

        // Shared queue
        Queue<BookingRequest> queue = new LinkedList<>();

        // Add requests
        queue.add(new BookingRequest("RES101", "Single"));
        queue.add(new BookingRequest("RES102", "Single"));
        queue.add(new BookingRequest("RES103", "Single")); // extra
        queue.add(new BookingRequest("RES104", "Double"));

        RoomInventory inventory = new RoomInventory();

        // Multiple threads
        Thread t1 = new BookingProcessor(queue, inventory);
        Thread t2 = new BookingProcessor(queue, inventory);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        inventory.display();
    }
}