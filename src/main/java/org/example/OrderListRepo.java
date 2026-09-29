package org.example;

import java.lang.classfile.MethodSignature;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderListRepo implements OrderRepoInterface {

   /* public OrderListRepo(List<Order> listOrders) {
        this.listOrders = listOrders;
    }*/

    //Properties
    List<Order> listOrders = new ArrayList<>();

    // Getter & Setter
    public List<Order> getListOrders() {
        return listOrders;
    }

    public void setListOrders(List<Order> listOrders) {
        this.listOrders = listOrders;
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
        return listOrders;
    }

    // Java Methods
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
