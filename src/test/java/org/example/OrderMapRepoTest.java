package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapRepoTest {

    @Test
    void cutToHistoryList_shouldCopyTwoElementsFromListToHistory() {
        OrderRepoInterface orderRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(orderRepo);
        Order order = new Order("2222", new ArrayList<>());
        Order order2 = new Order("1111", new ArrayList<>());
        Order order3 = new Order("2333", new ArrayList<>());
        Order order4 = new Order("1211", new ArrayList<>());
        orderRepo.addOrder(order);
        orderRepo.addOrder(order2);
        //orderRepo.addOrder(order3);
        //orderRepo.addOrder(order4);

        orderRepo.cutToHistoryList(order);
        orderRepo.cutToHistoryList(order2);
        assertEquals(2, shopService.getOrderRepo().getAllHistory().size());
        assertEquals(0, shopService.getOrderRepo().getAll().size());

    }

    @Test
    void cutToHistoryList_shouldNotCopyFromListToHistoryByGivenNull() {
        OrderRepoInterface orderRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(orderRepo);
        Order order = new Order("2222", null);
        Order order2 = new Order("1111", null);
        Order order3 = new Order("2333", new ArrayList<>());
        Order order4 = new Order("1211", new ArrayList<>());

        orderRepo.cutToHistoryList(null);

        assertEquals(0, shopService.getOrderRepo().getAllHistory().size());
        assertEquals(0, shopService.getOrderRepo().getAll().size());
    }
}