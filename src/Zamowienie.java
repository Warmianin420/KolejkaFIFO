public class Zamowienie {
    private String customerName;
    private String productName;
    private int quantity;

    public Zamowienie(String customerName, String productName, int quantity) {
        this.customerName = customerName;
        this.productName = productName;
        this.quantity = quantity;
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
        return customerName + "," + productName + "," + quantity;
    }
}