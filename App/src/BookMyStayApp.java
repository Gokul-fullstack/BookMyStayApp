import java.io.*;
import java.util.*;

// Serializable Reservation class
class Reservation implements Serializable {
    String reservationId;
    String roomType;

    public Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }

    public String toString() {
        return "Reservation ID: " + reservationId + ", Room Type: " + roomType;
    }
}

// Inventory class (Serializable)
class RoomInventory implements Serializable {
    Map<String, Integer> inventory = new HashMap<>();

    public void addRoom(String type, int count) {
        inventory.put(type, count);
    }

    public void showInventory() {
        System.out.println("\nInventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + " -> " + inventory.get(key));
        }
    }
}

public class BookMyStayApp {

    static final String FILE_NAME = "data.ser";

    // SAVE DATA
    public static void saveData(RoomInventory inv, List<Reservation> history) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(inv);
            oos.writeObject(history);
            System.out.println("\nData saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving data");
        }
    }

    // LOAD DATA
    public static Object[] loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            RoomInventory inv = (RoomInventory) ois.readObject();
            List<Reservation> history = (List<Reservation>) ois.readObject();
            System.out.println("\nData loaded successfully!");
            return new Object[]{inv, history};
        } catch (Exception e) {
            System.out.println("\nNo previous data found. Starting fresh...");
            return null;
        }
    }

    public static void main(String[] args) {

        RoomInventory inventory;
        List<Reservation> history;

        // LOAD EXISTING DATA
        Object[] data = loadData();

        if (data != null) {
            inventory = (RoomInventory) data[0];
            history = (List<Reservation>) data[1];
        } else {
            // FRESH START
            inventory = new RoomInventory();
            history = new ArrayList<>();

            inventory.addRoom("Single", 5);
            inventory.addRoom("Double", 3);
        }

        // SHOW CURRENT DATA
        inventory.showInventory();

        System.out.println("\nBooking History:");
        for (Reservation r : history) {
            System.out.println(r);
        }

        // ADD NEW BOOKING
        Reservation r1 = new Reservation("R101", "Single");
        history.add(r1);

        System.out.println("\nNew Booking Added:");
        System.out.println(r1);

        // SAVE DATA
        saveData(inventory, history);
    }
}