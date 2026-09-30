package org.example;

import java.lang.classfile.MethodSignature;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderListRepo implements OrderRepoInterface {

    //Properties
    List<Order> listOrders = new ArrayList<>();
    List<Order> listOrdersHistory = new ArrayList<>();

    // Getter & Setter
    public List<Order> getListOrdersHistory() {
        return listOrdersHistory;
    }

    public List<Order> getListOrders() {
        return listOrders;
    }

    public void setListOrders(List<Order> listOrders) {
        this.listOrders = listOrders;
    }

    //Methods
    @Override
    public void addOrder(Order order) {
        this.listOrders.add(order);
    }

    @Override
    public void removeOrder(String orderId) {
        for (int i = 0; i < getListOrders().size(); i++) {
            if (getListOrders().get(i).orderId().equals(orderId)) {
                getListOrders().remove(i);
                break;
            }
        }
    }

    @Override
    public Order getById(String orderId) {
        if (getListOrders() != null) {
            for (int i = 0; i < getListOrders().size(); i++) {
                if (orderId.equals(getListOrders().get(i).orderId())) {
                    return getListOrders().get(i);
                }
            }
        }
        return null;
    }

    //Wie viele gleiche Produkte habe ich in meinen Order
    //quantity = 0 bedeutet Product nicht auf Lager

    // Java Methods
    @Override
    public List<Order> getAll() {
        return listOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderListRepo that = (OrderListRepo) o;
        return Objects.equals(listOrders, that.listOrders);
    }

    @Override
    public String toString() {
        return "OrderListRepo{" +
                "listOrders=" + listOrders +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(listOrders);
    }
}
