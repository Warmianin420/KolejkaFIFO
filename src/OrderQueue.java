import java.util.LinkedList;
import java.util.Queue;

public class OrderQueue {
    private Queue<Zamowienie> orders;

    public OrderQueue() {
        this.orders = new LinkedList<>();
    }

    public void addOrder(Zamowienie order) {
        orders.offer(order);
    }

    public Queue<Zamowienie> getOrder() {
        return orders;
    }

    public void displayOrders() {
        for (Zamowienie order : orders) {
            System.out.println(order);
        }
    }

    public Zamowienie peek() {
        return orders.peek();
    }

    public boolean isEmpty() {
        return orders.isEmpty();
    }

    public void poll() {
        orders.poll();
    }

    public void clear() {
        orders.clear();
    }
}
