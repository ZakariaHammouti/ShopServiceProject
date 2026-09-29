package org.example;


public class Main {
    static void main(String[] args) {


        // Produkte Erstellen
        Product product1 = new Product("Phone", "15", 253.75);
        Product product2 = new Product("Laptop", "20", 445.69);
        Product product3 = new Product("Television", "87", 385.39);
        Product product4 = new Product("Table", "12", 55.99);
        Product product5 = new Product("Headset", "35", 15.15);

        //add Products


        //Interface und Shop Service zum Aufgeben einer Bestellung
        OrderRepoInterface orderRepoInterface = new OrderListRepo();
        ShopService shopService = new ShopService(orderRepoInterface);

        System.out.println(shopService.getProductRepo().getListProducts().size());
        shopService.getProductRepo().removeProduct("15");
        System.out.println(shopService.getProductRepo().getListProducts().size());


    }
}
