import java.util.HashMap;

class RoomInventory {

    HashMap<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 0);
        inventory.put("Suite Room", 2);
    }

    void searchRooms() {

        System.out.println("Available Rooms:");

        for (String room : inventory.keySet()) {

            int available = inventory.get(room);

            if (available > 0) {
                System.out.println(room + " : " + available);
            }
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=== Book My Stay App v4.0 ===");

        RoomInventory inventory = new RoomInventory();
        inventory.searchRooms();
    }
}