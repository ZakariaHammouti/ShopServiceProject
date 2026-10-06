package org.example;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMapRepo implements OrderRepoInterface {

    //Properties
    private Map<String, Order> orderMap = new HashMap<>();
    private Map<String, Order> orderMapHistory = new HashMap<>();

    // Getter & Setter
    public Map<String, Order> getOrderMapHistory() {
        return orderMapHistory;
    }

    public Map<String, Order> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(Map<String, Order> orderMap) {
        this.orderMap = orderMap;
    }

    @Override
    public void cutToHistoryList(Order order) {
        if (order != null) {
            if (getOrderMap() != null && getOrderMapHistory() != null) {
                getOrderMapHistory().put(order.orderId(), order);
                removeOrder(order.orderId());
            }
        }
    }

    //Methods
    @Override
    public void addOrder(Order order) {
        //ZonedDataTime
        Instant instant = Instant.now();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = instant.atZone(zoneId).withNano(0);
        Order updatedOrder = order.withOrderTime(zonedDateTime);

        getOrderMap().put(updatedOrder.orderId(), updatedOrder);
    }

    @Override
    public void removeOrder(String orderId) {
        getOrderMap().remove(orderId);
    }

    @Override
    public Order getById(String productId) throws OrderNotFound {
        //remove later
        Order order = getOrderMap().get(productId);
        if (order == null) {
            throw new OrderNotFound("Order nicht gefunden: " + productId);
        }
        return order;
    }

    @Override
    public List<Order> getAll() {
        //Hier sind das Values von orderMap
        return orderMap.values().stream().toList();
    }

    @Override
    public List<Order> getAllHistory() {
        //Hier sind das Values von orderMap
        return orderMapHistory.values().stream().toList();
    }

    @Override
    public String toString() {
        return "OrderMapRepo{" +
                "orderMap=" + orderMap +
                '}';
    }
}
