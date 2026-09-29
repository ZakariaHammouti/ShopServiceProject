package org.example;


import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main(String[] args) {

        // Produkte Erstellen, von denen man OrderList erstellen kann
        Product product1 = new Product("Phone", "15", 253.75);
        Product product2 = new Product("Laptop", "20", 445.69);
        Product product3 = new Product("Television", "87", 385.39);
        Product product4 = new Product("Table", "12", 55.99);
        Product product5 = new Product("Headset", "35", 15.15);
        Product product9 = new Product("USBStick", "42", 12.99);
        Product product10 = new Product("AirFryer", "65", 100.15);

        //erstelle ProductList
        List<Product> listOfSelectedProducts = new ArrayList<>();
        listOfSelectedProducts.add(product1);
        listOfSelectedProducts.add(product2);
        listOfSelectedProducts.add(product3);

        //erstelle Order
        Order order1 = new Order("1234", listOfSelectedProducts);
        Order order2 = new Order("3456", listOfSelectedProducts);

        //################## Mit OrdrListRepo bestellen ##############
        //Interface und Shop Service zum Aufgeben einer Bestellung
       /* OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);

        //Füge Bestellungen zu Liste hinzu
        shopService.getOrderListRepo().addOrder(order1);
        shopService.getOrderListRepo().addOrder(order2);
        System.out.println("shopService.getOrderListRepo():    " + shopService.getOrderListRepo());

        //Bestellung mit der Id aufgeben
        //shopService.getOrderListRepo().removeOrder("1234");
        shopService.placeOrder("1234");*/
        //#################### Ende Bestellung mit OrdListRepo


        //################# Bestellen mit OrderRepoMap ############
        OrderRepoInterface orderMapRepo = new OrderListRepo();
        ShopService shopService2 = new ShopService(orderMapRepo);

        shopService2.getOrderMapRepo().addOrder(order1);
        shopService2.getOrderMapRepo().addOrder(order2);
        //System.out.println("shopService.getOrderMapRepo():    " + shopService2.getOrderMapRepo());
        //shopService2.getOrderMapRepo().removeOrder("1234");
        shopService2.placeOrderWithMap("1234");

        shopService2.getOrderMapRepo().removeOrder("1234");

        shopService2.placeOrderWithMap("1234");
        //################## EndeBestellung mit OrderRepoMap ######


    }
}
