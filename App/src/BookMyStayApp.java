import java.util.HashMap;
import java.util.Scanner;

class RoomInventory {

    HashMap<String, Integer> rooms = new HashMap<>();

    public void addRoom(String type, int count) {
        rooms.put(type, count);
    }

    public void searchRoom(String type) {
        if (rooms.containsKey(type)) {
            System.out.println(type + " rooms available: " + rooms.get(type));
        } else {
            System.out.println("Room type not found");
        }
    }

    public void bookRoom(String type) {
        if (rooms.containsKey(type) && rooms.get(type) > 0) {
            rooms.put(type, rooms.get(type) - 1);
            System.out.println("Booking Confirmed for " + type + " room");
        } else {
            System.out.println("Room not available");
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        RoomInventory inventory = new RoomInventory();

        inventory.addRoom("Single", 5);
        inventory.addRoom("Double", 3);
        inventory.addRoom("Suite", 2);

        System.out.println("Enter room type to book:");
        String type = sc.nextLine();

        inventory.searchRoom(type);
        inventory.bookRoom(type);
    }
}