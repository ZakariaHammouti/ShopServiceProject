package org.example;

import java.util.List;

public interface OrderRepoInterface {

    public void addOrder(Order order);

    public void removeOrder(String orderId);

    public Order getById(String orderId);

    public List<Order> getAll();
    
}
