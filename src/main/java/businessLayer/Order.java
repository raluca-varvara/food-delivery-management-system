package businessLayer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Order implements Serializable {
    private int clientId;
    private int orderId;
    private LocalDateTime orderDate;
    private double totalPrice;

    public Order(int clientId, int orderId, LocalDateTime orderDate, double totalPrice) {
        this.clientId = clientId;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return clientId == order.clientId && orderId == order.orderId && Objects.equals(orderDate, order.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, orderId, orderDate);
    }

    public int getClientId() {
        return clientId;
    }

    public int getOrderId() {
        return orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "clientId=" + clientId +
                ", orderId=" + orderId +
                ", orderDate=" + orderDate +
                "}\n";
    }
}
