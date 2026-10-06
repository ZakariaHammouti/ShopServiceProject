package org.example;

import java.lang.classfile.MethodSignature;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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


    @Override
    public void cutToHistoryList(Order order) {
        if (order != null) {
            if (getListOrders() != null && getListOrdersHistory() != null) {
                getListOrdersHistory().add(order);
                removeOrder(order.orderId());
            }
        }
    }

    //Methods
    @Override
    public void addOrder(Order order) {

        //ZonedDataTime
        ZonedDateTime zonedDateTime = ZonedDateTime.now().withNano(0);
        // ZonedDateTime zonedDataTime = ZonedDateTime.parse(formattedDate, formattter);

        Order updatedOrder = order.withOrderTime(zonedDateTime);

        this.listOrders.add(updatedOrder);
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
    public Order getById(String orderId) throws OrderNotFound {
        if (getListOrders() != null) {
            for (int i = 0; i < getListOrders().size(); i++) {
                if (orderId.equals(getListOrders().get(i).orderId())) {
                    return getListOrders().get(i);
                }
            }
        }
        throw new OrderNotFound("Order nicht gefunden: " + orderId);
    }

    //Wie viele gleiche Produkte habe ich in meinen Order
    //quantity = 0 bedeutet Product nicht auf Lager

    // Java Methods
    @Override
    public List<Order> getAll() {
        return listOrders;
    }

    @Override
    public List<Order> getAllHistory() {
        return getListOrdersHistory();
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
