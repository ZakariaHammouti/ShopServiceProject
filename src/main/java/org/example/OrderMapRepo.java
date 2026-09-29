package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMapRepo implements OrderRepoInterface {

    //Properties
    private Map<Integer, Order> orderMap = new HashMap<>();
    private List<Order> orderList = new ArrayList<>();

    //Getter & Setter
    public Map<Integer, Order> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(Map<Integer, Order> orderMap) {
        this.orderMap = orderMap;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    //Methods
    @Override
    public void addOrder() {

    }

    @Override
    public void removeOrder() {

    }

    public Order getById() {
        return getById(0);
    }

    @Override
    public Order getById(int ProductId) {
        //remove later
        return new Order();
    }

    @Override
    public List<Order> getAll() {
        //Hier sind das Values von orderMap
        return orderList;
    }

}
