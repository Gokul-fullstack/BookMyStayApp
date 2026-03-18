import java.util.HashMap;

/**
 * Book My Stay App
 * Use Case 3: Centralized Room Inventory
 * @version 3.0
 */

class RoomInventory {

    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public void displayInventory() {

        System.out.println("Room Availability:");

        for (String room : inventory.keySet()) {
            System.out.println(room + " : " + inventory.get(room));
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=== Book My Stay App v3.0 ===");

        RoomInventory inventory = new RoomInventory();
        inventory.displayInventory();
    }
}