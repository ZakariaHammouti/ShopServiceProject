package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    void placeOrder_ByGivenValidOrderId() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("products_100.csv"));

        List<String[]> dataList = new ArrayList<>();

        // Erste Zeile überspringen (Header)
        for (int i = 1; i < lines.size(); i++) {
            dataList.add(lines.get(i).split(","));
        }

        // ShopService mit den CSV-Daten erstellen
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo, dataList);

        List<Product> listOfSelectedProducts1 = new ArrayList<>();
        listOfSelectedProducts1.add(shopService.getProductRepo().getProduct("1"));
        listOfSelectedProducts1.add(shopService.getProductRepo().getProduct("2"));
        Order order = new Order("1234", listOfSelectedProducts1);
        shopService.getOrderRepo().addOrder(order);
        assertTrue(shopService.placeOrder(order.orderId()));
    }

    @Test
    void isAvailable_ByGivenValidProductId() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("products_100.csv"));

        List<String[]> dataList = new ArrayList<>();

        // Erste Zeile überspringen (Header)
        for (int i = 1; i < lines.size(); i++) {
            dataList.add(lines.get(i).split(","));
        }

        // ShopService mit den CSV-Daten erstellen
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo, dataList);

        List<Product> listOfSelectedProducts1 = new ArrayList<>();
        listOfSelectedProducts1.add(shopService.getProductRepo().getListProducts().get(0));
        Order order = new Order("1234", listOfSelectedProducts1);
        shopService.getOrderRepo().addOrder(order);

        assertTrue(shopService.isAvailable(shopService.getProductRepo().getListProducts().get(0).id()));
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
    void isAvailable_ByCallingWithValidProductId() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("products_100.csv"));

        List<String[]> dataList = new ArrayList<>();

        // Erste Zeile überspringen (Header)
        for (int i = 1; i < lines.size(); i++) {
            dataList.add(lines.get(i).split(","));
        }

        // ShopService mit den CSV-Daten erstellen
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo, dataList);

        assertTrue(shopService.isAvailable("15"));
    }

    @Test
    void isAvailable_ByCallingWithValidNotAvailableProductId() {
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);
        assertFalse(shopService.isAvailable("17"));
    }


}