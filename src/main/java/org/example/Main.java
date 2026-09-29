package org.example;


import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main(String[] args) {

        // Produkte Erstellen, von denen man OrderList erstellen kann
        Product product1 = new Product("Phone", "15", 253.75);
        Product product2 = new Product("Laptop", "20", 445.69);
        Product product3 = new Product("SmartWatch", "87", 385.39);
        Product product4 = new Product("Table", "12", 55.99);
        Product product5 = new Product("Headset", "35", 15.15);
        Product product6 = new Product("Phone_Samsung", "45", 353.75);
        Product product7 = new Product("Laptop_Dell", "22", 745.69);
        Product product8 = new Product("Smart_TV", "17", 354.39);
        Product product9 = new Product("USBStick", "42", 12.99);
        Product product10 = new Product("AirFryer", "65", 100.15);
        //erstelle ProductList
        List<Product> listOfSelectedProducts1 = new ArrayList<>();
        listOfSelectedProducts1.add(product1);
        listOfSelectedProducts1.add(product2);
        listOfSelectedProducts1.add(product3);

        List<Product> listOfSelectedProducts2 = new ArrayList<>();
        listOfSelectedProducts2.add(product6);
        listOfSelectedProducts2.add(product7);
        listOfSelectedProducts2.add(product10);

        //erstelle Order
        Order order1 = new Order("1234", listOfSelectedProducts1);
        Order order2 = new Order("3456", listOfSelectedProducts2);

        //################## Mit OrdrListRepo bestellen ##############
        //Interface und Shop Service zum Aufgeben einer Bestellung
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);

        //Füge Bestellungen zu Liste hinzu
        shopService.getOrderRepo().addOrder(order1);
        shopService.getOrderRepo().addOrder(order2);
        //System.out.println("shopService.getOrderRepo():    " + shopService.getOrderRepo());

        //Bestellung mit der Id aufgeben
        shopService.placeOrder("1234");
        //System.out.println("List Order: " + shopService.getOrderRepo().getAll());
        shopService.getOrderRepo().removeOrder("1234");
        shopService.placeOrder("1234");
        //System.out.println("List Order: " + shopService.getOrderRepo().getAll());

        shopService.getOrderRepo().removeOrder("3456");
        shopService.placeOrder("3456");
        //System.out.println("List Order: " + shopService.getOrderRepo().getAll());
        //#################### Ende Bestellung mit OrdListRepo

        //################# Bestellen mit OrderRepoMap ############
        OrderRepoInterface orderMapRepo = new OrderMapRepo();
        ShopService shopService2 = new ShopService(orderMapRepo);

        shopService2.getOrderRepo().addOrder(order1);
        shopService2.getOrderRepo().addOrder(order2);
        //System.out.println("shopService.getOrderMapRepo():    " + shopService2.getOrderMapRepo());
        //shopService2.getOrderMapRepo().removeOrder("1234");
        shopService2.placeOrder("1234");
        //System.out.println("List Order: " + shopService2.getOrderRepo().getAll());

        shopService2.getOrderRepo().removeOrder("1234");
        shopService2.getOrderRepo().removeOrder("3456");

        shopService2.placeOrder("1244");
        //System.out.println("List Order: " + shopService2.getOrderRepo().getAll());
        shopService2.placeOrder("3456");
        //System.out.println("List Order: " + shopService2.getOrderRepo().getAll());
        //################## Ende Bestellung mit OrderRepoMap ######
    }
}
