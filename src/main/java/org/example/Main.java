package org.example;


import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main(String[] args) {


        // Produkte Erstellen
        Product product1 = new Product("Phone", "15", 253.75);
        Product product2 = new Product("Laptop", "20", 445.69);
        Product product3 = new Product("Television", "87", 385.39);
        Product product4 = new Product("Table", "12", 55.99);
        Product product5 = new Product("Headset", "35", 15.15);

        Product product9 = new Product("USBStick", "42", 12.99);
        Product product10 = new Product("AirFryer", "65", 100.15);

        //create ProductList
        List<Product> listOfSelectedProducts = new ArrayList<>();
        listOfSelectedProducts.add(product1);
        listOfSelectedProducts.add(product9);
        listOfSelectedProducts.add(product10);

        //create Order
        Order order1 = new Order("1234", listOfSelectedProducts);
        Order order2 = new Order("3456", listOfSelectedProducts);

        //Erstmal Bestellungen erstelen dann können sie später anhand BestellId
        //aufgegeben werden

        //Interface und Shop Service zum Aufgeben einer Bestellung
        OrderRepoInterface orderListRepo = new OrderListRepo();

        ShopService shopService = new ShopService(orderListRepo);

        //Füge Bestellungen zu Liste hinzu
        //Bevor dessen prüfe ob sie verfügbar sind i Produkt list(Stockage)
        shopService.getOrderListRepo().addOrder(order1);
        shopService.getOrderListRepo().addOrder(order2);
        System.out.println("shopService.getOrderListRepo():    " + shopService.getOrderListRepo());

        shopService.placeOrder("1234");


    }
}
