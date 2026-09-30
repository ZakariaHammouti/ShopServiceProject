package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void placeOrder_ByGivenEmptyOrderId() {
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);

        List<Product> listOfSelectedProducts1 = new ArrayList<>();
        listOfSelectedProducts1.add(shopService.getProductRepo().getProduct("15"));
        Order order = new Order("1234", listOfSelectedProducts1);
        shopService.getOrderRepo().addOrder(order);

        assertFalse(shopService.placeOrder(""));
    }

    @Test
    void placeOrder_ByGivenNullOrderId() {
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);

        List<Product> listOfSelectedProducts1 = new ArrayList<>();
        listOfSelectedProducts1.add(shopService.getProductRepo().getProduct("15"));
        Order order = new Order("1234", listOfSelectedProducts1);
        //shopService.getOrderRepo().addOrder(order);

        assertFalse(shopService.placeOrder(null));
    }

    @Test
    void placeOrder_ByGivenValidOrderId() {
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);

        List<Product> listOfSelectedProducts1 = new ArrayList<>();
        listOfSelectedProducts1.add(shopService.getProductRepo().getProduct("15"));
        Order order = new Order("1234", listOfSelectedProducts1);
        shopService.getOrderRepo().addOrder(order);
        assertTrue(shopService.placeOrder(order.orderId()));
    }

    @Test
    void isAvailable_ByGivenValidProductId() {
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);

        List<Product> listOfSelectedProducts1 = new ArrayList<>();
        listOfSelectedProducts1.add(shopService.getProductRepo().getProduct("15"));
        Order order = new Order("1234", listOfSelectedProducts1);
        shopService.getOrderRepo().addOrder(order);

        assertTrue(shopService.isAvailable("15"));
    }

    @Test
    void isAvailable_ByGivenInvalidProductId() {
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);

        List<Product> listOfSelectedProducts1 = new ArrayList<>();
        listOfSelectedProducts1.add(shopService.getProductRepo().getProduct("15"));
        Order order = new Order("1234", listOfSelectedProducts1);
        shopService.getOrderRepo().addOrder(order);

        assertFalse(shopService.isAvailable("16"));
    }

    @Test
    void isAvailable_ByGivenNullProductId() {
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);

        List<Product> listOfSelectedProducts1 = new ArrayList<>();
        listOfSelectedProducts1.add(shopService.getProductRepo().getProduct("15"));
        Order order = new Order("1234", listOfSelectedProducts1);
        shopService.getOrderRepo().addOrder(order);

        assertFalse(shopService.isAvailable(null));
    }

    @Test
    void isAvailable_ByCallingWithEmptyList() {
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);
        shopService.getProductRepo().getListProducts().clear();
        assertFalse(shopService.isAvailable("15"));
    }

    @Test
    void isAvailable_ByCallingWithValidProductId() {
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);
        assertTrue(shopService.isAvailable("15"));
    }

    @Test
    void isAvailable_ByCallingWithValidNotAvailableProductId() {
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);
        assertFalse(shopService.isAvailable("17"));
    }


}