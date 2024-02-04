import java.util.LinkedList;
import java.util.Queue;

public class OrderQueue {
    private Queue<Zamowienie> orders;

    public OrderQueue() {
        this.orders = new LinkedList();
    }

    public void addOrder(Zamowienie order) {
        orders.offer(order);
    }

    public Zamowienie getOrder() {
        return orders.poll();
    }

    public void displayOrders() {
        for (Zamowienie order : orders) {
            System.out.println(order);
        }
    }

    // Tutaj można dodać metody do zapisu i odczytu kolejki do/z pliku
}
