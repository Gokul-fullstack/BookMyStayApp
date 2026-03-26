import java.util.*;
class AddOnService {
    String serviceName;
    double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }
}

// Manager class to handle services
class AddOnServiceManager {

    // Map<ReservationID, List of Services>
    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

    public void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    public void displayServices(String reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        System.out.println("Services for Reservation: " + reservationId);

        double totalCost = 0;

        for (AddOnService s : services) {
            System.out.println(s.serviceName + " - ₹" + s.cost);
            totalCost += s.cost;
        }

        System.out.println("Total Add-On Cost: ₹" + totalCost);
    }
}

// Main Class
public class BookMyStayApp {

    public static void main(String[] args) {

        // Create manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Sample reservation ID
        String reservationId = "RES101";

        // Create services
        AddOnService breakfast = new AddOnService("Breakfast", 500);
        AddOnService wifi = new AddOnService("WiFi", 200);
        AddOnService pickup = new AddOnService("Airport Pickup", 1000);

        // Add services to reservation
        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, wifi);
        manager.addService(reservationId, pickup);

        // Display services and total cost
        manager.displayServices(reservationId);
    }
}