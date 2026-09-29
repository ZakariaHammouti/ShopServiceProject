package org.example;

import java.util.List;

public interface OrderRepoInterface {

    public void addOrder();

    public void removeOrder();

    public Order getById(int ProductId);
    
    public List<Order> getAll();
}
