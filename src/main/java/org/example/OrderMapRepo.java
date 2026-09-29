package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMapRepo implements OrderRepoInterface {

    //Properties
    private Map<String, Order> orderMap = new HashMap<>();

    //Getter & Setter
    public Map<String, Order> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(Map<String, Order> orderMap) {
        this.orderMap = orderMap;
    }

    //Methods
    @Override
    public void addOrder(Order order) {
        getOrderMap().put(order.orderId(), order);
    }

    @Override
    public void removeOrder(String orderId) {
        getOrderMap().remove(orderId);
    }

    public Order getById() {
        return getById("0");
    }

    @Override
    public Order getById(String productId) {
        //remove later
        return getOrderMap().get(productId);
    }

    @Override
    public List<Order> getAll() {
        //Hier sind das Values von orderMap
        return orderMap.values().stream().toList();
    }

    @Override
    public String toString() {
        return "OrderMapRepo{" +
                "orderMap=" + orderMap +
                '}';
    }
}
