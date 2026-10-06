package org.example;

import java.util.List;

public interface OrderRepoInterface {

    public void addOrder(Order order);

    public void removeOrder(String orderId);

    public Order getById(String orderId) throws OrderNotFound;

    public List<Order> getAll();

    public void cutToHistoryList(Order order);

    public List<Order> getAllHistory();
}
