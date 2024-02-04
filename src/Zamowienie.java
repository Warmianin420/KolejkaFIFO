public class Zamowienie {
    private static int nextId = 1; // Generator unikalnych ID dla zamówień
    private int id;
    private String customerName;
    private String productName;
    private int quantity;

    public Zamowienie(String customerName, String productName, int quantity) {
        this.id = nextId++;
        this.customerName = customerName;
        this.productName = productName;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Zamowienie{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}