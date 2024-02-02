public class Main {
    public static void main(String[] args) {
        OrderQueue orderQueue = new OrderQueue();

        // Dodawanie zamówień
        orderQueue.addOrder(new Zamowienie("Jan Kowalski", "Laptop", 1));
        orderQueue.addOrder(new Zamowienie("Anna Nowak", "Smartfon", 2));

        // Wyświetlanie wszystkich zamówień w kolejce
        System.out.println("Zamówienia w kolejce:");
        orderQueue.displayOrders();

        // Pobieranie zamówienia z kolejki
        Zamowienie processedOrder = orderQueue.getOrder();
        System.out.println("Przetwarzanie zamówienia: " + processedOrder);

        // Wyświetlanie pozostałych zamówień w kolejce
        System.out.println("Pozostałe zamówienia w kolejce:");
        orderQueue.displayOrders();
    }
}
