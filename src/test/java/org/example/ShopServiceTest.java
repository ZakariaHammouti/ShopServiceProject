package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void placeOrder_ByGivenEmptyOrderId() throws OrderNotFound {
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);

        List<Product> listOfSelectedProducts1 = new ArrayList<>();
        listOfSelectedProducts1.add(shopService.getProductRepo().getProduct("15").orElse(null));
        Order order = new Order("1234", listOfSelectedProducts1);
        shopService.getOrderRepo().addOrder(order);

        assertThrows(OrderNotFound.class, () -> shopService.placeOrder(""));
    }

    @Test
    void placeOrder_ByGivenNullOrderId() throws OrderNotFound {
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);

        List<Product> listOfSelectedProducts1 = new ArrayList<>();
        Product result = shopService.getProductRepo().getProduct("15").orElse(null);
        listOfSelectedProducts1.add(result);
        Order order = new Order("1234", listOfSelectedProducts1);
        //shopService.getOrderRepo().addOrder(order);

        assertThrows(OrderNotFound.class, () -> shopService.placeOrder(""));
    }

    @Test
    void placeOrder_ByGivenValidOrderId() throws IOException, OrderNotFound {
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
        Optional<Product> result = shopService.getProductRepo().getProduct("1");
        listOfSelectedProducts1.add(result.get());
        Optional<Product> result2 = shopService.getProductRepo().getProduct("2");
        listOfSelectedProducts1.add(result2.get());
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
        ProductRepo productRepo = new ProductRepo();
        //4000000000020,Schokolade Zartbitter,2.19
        List<String[]> data = new ArrayList<>();
        String[] dummyProduct = {"1", "Schokolade", "2.19"};
        data.add(dummyProduct);
        ShopService shopService = new ShopService(orderListRepo, data);


        List<Product> listOfSelectedProducts1 = new ArrayList<>();
        Product result = shopService.getProductRepo().getProduct("1").
                orElse(null);
        listOfSelectedProducts1.add(result);
        Order order = new Order("1234", listOfSelectedProducts1);
        shopService.getOrderRepo().addOrder(order);

        assertFalse(shopService.isAvailable("2"));
    }

    @Test
    void isAvailable_ByGivenNullProductId() {
        ProductRepo productRepo = new ProductRepo();
        //4000000000020,Schokolade Zartbitter,2.19
        OrderRepoInterface orderListRepo = new OrderListRepo();
        List<String[]> data = new ArrayList<>();
        String[] dummyProduct = {"1", "Schokolade", "2.19"};
        data.add(dummyProduct);
        ShopService shopService = new ShopService(orderListRepo, data);
        List<Product> listOfSelectedProducts1 = new ArrayList<>();
        Product result = shopService.getProductRepo().getProduct("1").
                orElse(null);
        listOfSelectedProducts1.add(result);

        listOfSelectedProducts1.add(shopService.getProductRepo().getProduct("1").orElse(null));
        Order order = new Order("1234", listOfSelectedProducts1);
        shopService.getOrderRepo().addOrder(order);

        assertFalse(shopService.isAvailable(null));
    }

    @Test
    void isAvailable_ByCallingWithEmptyList() {
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);
        shopService.getProductRepo().getListProducts().clear();
        assertFalse(shopService.isAvailable("1"));
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


    @Test
    void getOrderStatus_ShouldReturnListwithProssesing() {
        //Given
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);
        OrderStatus status = OrderStatus.PROCESSING;
        List<Product> listOrdererd = new ArrayList<>();
        listOrdererd.add(new Product("1", "11", 3, true, 3));
        listOrdererd.add(new Product("2", "22", 3, true, 3));
        Order order = new Order("123", listOrdererd);
        Order order2 = new Order("123", listOrdererd);
        Order order3 = order2.withStatus(OrderStatus.IN_DELIVERY);

        orderListRepo.addOrder(order);
        orderListRepo.addOrder(order2);
        orderListRepo.addOrder(order3);

        //expected 2 Proccsing orders
        assertEquals(2, shopService.getOrderStatus(status).orElse(List.of()).size());
    }

    @Test
    void getOrderStatus_ShouldReturnListwithInDelivery() {
        //Given
        OrderStatus statusPassToFunction = OrderStatus.IN_DELIVERY;
        //Crealie list Orders
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);
        OrderStatus status = OrderStatus.PROCESSING;
        List<Product> listOrdererd = new ArrayList<>();
        listOrdererd.add(new Product("1", "11", 3, true, 3));
        listOrdererd.add(new Product("2", "22", 3, true, 3));
        Order order = new Order("123", listOrdererd);
        Order order2 = new Order("123", listOrdererd);
        Order order3 = order2.withStatus(OrderStatus.IN_DELIVERY);

        orderListRepo.addOrder(order);
        orderListRepo.addOrder(order2);
        orderListRepo.addOrder(order3);

        //expected 2 Proccsing orders
        assertEquals(1, shopService.getOrderStatus(statusPassToFunction).orElse(List.of()).size());
    }

    @Test
    void getOrderStatus_ShouldReturnZero() {
        //Given
        OrderStatus statusPassToFunction = OrderStatus.IN_DELIVERY;
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);
        //expected 2 Proccsing orders
        assertEquals(0, shopService.getOrderStatus(null).orElse(List.of()).size());
    }

    @Test
    void getOrderStatus_ShouldReturnNull() {
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);
        //expected 2 Proccsing orders
        assertTrue(shopService.getOrderStatus(null).isEmpty());
    }
}
