public class Zamowienie {
//    private static int nextId = 1; // Generator unikalnych ID dla zamówień
    private int id;
    private String customerName;
    private String productName;
    private int quantity;

    public Zamowienie(int id, String customerName, String productName, int quantity) {
        this.id = id;
        this.customerName = customerName;
        this.productName = productName;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return id + "," + customerName + "," + productName + "," + quantity;
    }
}