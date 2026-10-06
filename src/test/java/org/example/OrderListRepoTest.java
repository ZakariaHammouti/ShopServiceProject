package org.example;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    void getById_ShouldReturnNullByEmptyList() throws OrderNotFound {
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);
        Order order = new Order("2222", new ArrayList<>());
        Order order2 = new Order("1111", new ArrayList<>());

        assertThrows(OrderNotFound.class, () -> orderListRepo.getById(order.orderId()));
    }

    @Test
    void getById_ShouldReturnNotNullByCallingWithFullList() throws OrderNotFound {
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

        Order order = new Order("2222", listOfSelectedProducts1);
        Order order2 = new Order("1111", listOfSelectedProducts1);
        orderListRepo.addOrder(order);
        assertDoesNotThrow(() -> orderListRepo.getById(order.orderId()));
    }

    @Test
    void getById_ShouldReturnNotNullByCallingWithEmptyList() throws OrderNotFound {
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ProductRepo productRepo = new ProductRepo();
        //4000000000020,Schokolade Zartbitter,2.19
        List<String[]> data = new ArrayList<>();
        String[] dummyProduct = {"1", "Schokolade", "2.19"};
        data.add(dummyProduct);
        ShopService shopService = new ShopService(orderListRepo, data);

        List<Product> listOfSelectedProducts1 = new ArrayList<>();
        Product result = shopService.getProductRepo().getProduct("15").
                orElse(null);

        listOfSelectedProducts1.add(result);

        Order order = new Order("2222", listOfSelectedProducts1, OrderStatus.PROCESSING, ZonedDateTime.now());
        Order order2 = new Order("1111", listOfSelectedProducts1, OrderStatus.PROCESSING, ZonedDateTime.now());
        orderListRepo.addOrder(order);
        orderListRepo.removeOrder(order.orderId());
        assertThrows(OrderNotFound.class, () -> orderListRepo.getById(order.orderId()));
    }

    @Test
    void addOrder_ShouldSetTimeToNow() throws OrderNotFound {
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ProductRepo productRepo = new ProductRepo();
        //4000000000020,Schokolade Zartbitter,2.19
        List<String[]> data = new ArrayList<>();
        String[] dummyProduct = {"1", "Schokolade", "2.19"};
        data.add(dummyProduct);
        ShopService shopService = new ShopService(orderListRepo, data);

        List<Product> listOfSelectedProducts1 = new ArrayList<>();
        Product result = shopService.getProductRepo().getProduct("15").
                orElse(null);

        listOfSelectedProducts1.add(result);

        Order order = new Order("2222", listOfSelectedProducts1);
        Order order2 = new Order("1111", listOfSelectedProducts1);

        orderListRepo.addOrder(order);

        ZonedDateTime excpected = ZonedDateTime.now().withNano(0);
        ZonedDateTime actual =
                orderListRepo.getById(order.orderId()).orderTime();

        assertEquals(excpected,
                actual);
    }

    @Test
    void addOrder_ShouldOnlyUpdateTheDateByGivenOrder() throws OrderNotFound {
        //Given
        //Crealie list Orders
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);
        List<Product> listOrdererd = new ArrayList<>();
        listOrdererd.add(new Product("1", "11", 3, true, 3));
        listOrdererd.add(new Product("2", "22", 3, true, 3));
        Order order = new Order("123", listOrdererd);
        Order order2 = new Order("122", listOrdererd);
        Order order3 = new Order("124", listOrdererd);
        orderListRepo.addOrder(order3);

        assertNotNull(shopService.getOrderRepo().getById("124").orderTime());
    }
}