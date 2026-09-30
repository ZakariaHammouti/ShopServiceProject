package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderListRepoTest {


    @Test
    void removeOrder_CheckListSizeAfterRemovingOrder() {

        //orderListRepo.
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);
        Order order = new Order("2222", new ArrayList<>());
        Order order2 = new Order("1111", new ArrayList<>());
        orderListRepo.addOrder(order);
        orderListRepo.addOrder(order2);

        orderListRepo.removeOrder("1111");
        assertEquals(1, orderListRepo.getAll().size());

    }

    @Test
    void removeOrder_CheckListSizeAfterRemovingNotExistingOrder() {
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);
        Order order = new Order("2222", new ArrayList<>());
        Order order2 = new Order("1111", new ArrayList<>());
        orderListRepo.addOrder(order);
        orderListRepo.addOrder(order2);

        orderListRepo.removeOrder("111");
        assertEquals(2, orderListRepo.getAll().size());
    }

    @Test
    void removeOrder_CheckListSizeAfterRemovingAll() {
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);
        Order order = new Order("2222", new ArrayList<>());
        Order order2 = new Order("1111", new ArrayList<>());
        orderListRepo.addOrder(order);
        orderListRepo.addOrder(order2);
        orderListRepo.removeOrder("1111");
        orderListRepo.removeOrder("2222");
        assertEquals(0, orderListRepo.getAll().size());
    }

    @Test
    void getById_ShouldReturnNullByEmptyList() {
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);
        Order order = new Order("2222", new ArrayList<>());
        Order order2 = new Order("1111", new ArrayList<>());

        assertNull(orderListRepo.getById(order.orderId()));
    }

    @Test
    void getById_ShouldReturnNotNullByCallingWithFullList() {
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);
        List<Product> listOfSelectedProducts1 = new ArrayList<>();
        listOfSelectedProducts1.add(shopService.getProductRepo().getProduct("15"));
        Order order = new Order("2222", listOfSelectedProducts1);
        Order order2 = new Order("1111", listOfSelectedProducts1);
        orderListRepo.addOrder(order);
        assertNotNull(orderListRepo.getById(order.orderId()));
    }

    @Test
    void getById_ShouldReturnNotNullByCallingWithEmptyList() {
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);
        List<Product> listOfSelectedProducts1 = new ArrayList<>();
        listOfSelectedProducts1.add(shopService.getProductRepo().getProduct("15"));
        Order order = new Order("2222", listOfSelectedProducts1);
        Order order2 = new Order("1111", listOfSelectedProducts1);
        orderListRepo.addOrder(order);
        orderListRepo.removeOrder(order.orderId());
        assertNull(orderListRepo.getById(order.orderId()));
    }
}