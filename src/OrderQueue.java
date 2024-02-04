import java.util.LinkedList;
import java.util.Queue;

public class OrderQueue {
    private Queue<Zamowienie> orders;
    private int nextId;

    public OrderQueue() {
        this.orders = new LinkedList<>();
        this.nextId = 1; // Rozpoczynamy numerację ID od 1
    }

    public void addOrder(Zamowienie order) {
        order.setId(this.nextId++); // Ustawiamy ID dla zamówienia i inkrementujemy nextId
        orders.offer(order);
    }

    public int getNextId() {
        return this.nextId; // Zwraca następny unikalny identyfikator
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
        // Po usunięciu elementu nie zmniejszamy nextId, ponieważ chcemy unikać powtórzeń
    }

    public void clear() {
        orders.clear();
        this.nextId = 1; // Resetujemy licznik ID po wyczyszczeniu kolejki
    }
}
