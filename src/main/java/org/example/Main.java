package org.example;


import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main(String[] args) {

        // Produkte Erstellen, von denen man OrderList erstellen kann
      /*  Product product1 = new Product("Phone", "15", 253.75, true, 17);
        Product product2 = new Product("Laptop", "20", 445.69, true, 2);
        Product product3 = new Product("SmartWatch", "87", 385.39, true, 3);
        Product product4 = new Product("Table", "12", 55.99, true, 12);
        Product product5 = new Product("Headset", "35", 15.15, true, 1);
        Product product6 = new Product("Phone_Samsung", "45", 353.75, true, 1);
        Product product7 = new Product("Laptop_Dell", "22", 745.69, true, 1);
        Product product8 = new Product("Smart_TV", "17", 354.39, false, 0);
        Product product9 = new Product("USBStick", "42", 12.99, false, 0);
        Product product10 = new Product("AirFryer", "65", 100.15, false, 0);*/
        //erstelle ProductList


        //################## Erstelle ShopService mit OrderListRepo ##############
        //Interface und Shop Service zum Aufgeben einer Bestellung
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);

        List<Product> listOfSelectedProducts1 = new ArrayList<>();
        listOfSelectedProducts1.add(shopService.getProductRepo().getProduct("15"));
        //listOfSelectedProducts1.add(shopService.getProductRepo().getProduct("35"));

        List<Product> listOfSelectedProducts2 = new ArrayList<>();

        //Prüfen bevor Produkte eingefügt werden
        if (shopService.getProductRepo().isProductNull("20")) {

            listOfSelectedProducts2.add(shopService.getProductRepo().getProduct("45"));
        }
        if (shopService.getProductRepo().isProductNull("17")) {
            listOfSelectedProducts2.add(shopService.getProductRepo().getProduct("17"));
        }
        if (shopService.getProductRepo().isProductNull("452")) {
            listOfSelectedProducts2.add(shopService.getProductRepo().getProduct("452"));
        }
        if (shopService.getProductRepo().isProductNull("35")) {
            listOfSelectedProducts2.add(shopService.getProductRepo().getProduct("35"));
        }

        //erstelle Order
        Order order1 = new Order("1234", listOfSelectedProducts1);
        Order order2 = new Order("3456", listOfSelectedProducts2);

        //Füge Bestellungen zu Liste hinzu
        shopService.getOrderRepo().addOrder(order1);
        shopService.getOrderRepo().addOrder(order2);

        //Bestellung mit der Id aufgeben
        shopService.getOrderRepo().removeOrder("1234");
        shopService.placeOrder("3456");

        //#################### Ende Bestellung mit OrdListRepo ###################

        //################# Erstelle ShopService mit OrderMapRepo ###############
        OrderRepoInterface orderMapRepo = new OrderMapRepo();
        ShopService shopService2 = new ShopService(orderMapRepo);

        shopService2.getOrderRepo().addOrder(order1);
        shopService2.getOrderRepo().addOrder(order2);

        shopService2.placeOrder(order1.orderId());
        shopService2.placeOrder(order2.orderId());

        //Bestellungen die erfolgreich waren entfernen von der Order List
        //Und einfügen in HistoryList

    }
}
