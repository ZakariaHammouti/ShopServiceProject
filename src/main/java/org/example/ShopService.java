package org.example;

import java.util.ArrayList;
import java.util.List;

public class ShopService {

    // Produkte Erstellen
   /* Product product1 = new Product("Phone", "15", 253.75);
    Product product2 = new Product("Laptop", "20", 445.69);
    Product product3 = new Product("Television", "87", 385.39);
    Product product4 = new Product("Table", "12", 55.99);
    Product product5 = new Product("Headset", "35", 15.15);*/

    //Properties
    private ProductRepo productRepo;
    private OrderRepoInterface orderListRepo;
    private OrderRepoInterface orderMapRepo;

    //Constructor
    public ShopService(OrderRepoInterface orderListRepo) {
        this.productRepo = new ProductRepo();
        //this.orderListRepo = orderListRepo;
        this.orderMapRepo = orderListRepo;
    }

    //Getter und Setter
    public ProductRepo getProductRepo() {
        return productRepo;
    }

    public void setProductRepo(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public OrderRepoInterface getOrderListRepo() {
        return orderListRepo;
    }

    public void setOrderListRepo(OrderRepoInterface orderListRepo) {
        this.orderListRepo = orderListRepo;
    }

    public OrderRepoInterface getOrderMapRepo() {
        return orderMapRepo;
    }

    public void setOrderMapRepo(OrderRepoInterface orderMapRepo) {
        this.orderMapRepo = orderMapRepo;
    }

    //Methods
    //Schritt 1: Implementiere eine Methode zum Aufgeben einer neuen Bestellung.
    // Die Artikel werden später unter Angabe der Produkt Id bestellt.

    public void placeOrder(Order order) {
        //add to list or add to map
        this.orderListRepo.addOrder(order);
    }

    public void placeOrder(String orderId) {
        //hol die Bestellung mit der ID von d OrderRepoList
        if (getOrderListRepo() != null) {
            if (orderListRepo.getById(orderId) != null) {
                if (orderListRepo.getById(orderId).product() != null) {
                    for (int i = 0; i < orderListRepo.getById(orderId).product().size(); i++) {
                        isAvailable(orderListRepo.getById(orderId).product().get(i).id());
                    }
                }
            } else {
                System.out.println("Bestellung mit dieser Id: " + orderId + " ist nicht Verfügbar");
            }
        }
    }

    //Schritt 2: Prüfe, ob die bestellten Produkte existieren.
    // Wenn nicht, gib eine System.out.println-Nachricht aus.
    public boolean isAvailable(String productId) {
        //iteriere durch die Produkte und guck mal ob sie in Produktliste existieren
        int i = 0;
        for (i = 0; i < getProductRepo().getListProducts().size(); i++) {
            //System.out.println("II: " + i);
            if (getProductRepo().getListProducts().get(i).id().equals(productId)) {
                System.out.println("Produkt verfügbar: " + getProductRepo().getListProducts().get(i));
                return true;
            }
        }
        System.out.println("Produkt nicht verfügbar: " + getProductRepo().getListProducts().get(i));
        return false;
    }

    public void placeOrderWithMap(String orderId) {
        //Ist d Order in  Map z finden
        if (getOrderMapRepo().getById(orderId) != null) {

            System.out.println("Order Exists");
            for (int i = 0; i < getOrderMapRepo().getById(orderId).product().size(); i++) {
                System.out.println("I: " + i);
                isAvailableWithMap(getOrderMapRepo().getById(orderId).product().get(i).id());
            }
        } else {
            System.out.println("Order does not exists");
        }
    }


    public boolean isAvailableWithMap(String productId) {
        int i = 0;
        for (i = 0; i < getProductRepo().getListProducts().size(); i++) {
            System.out.println("II: " + i);
            if (getProductRepo().getListProducts().get(i).id().equals(productId)) {
                System.out.println("Produkt verfügbar: " + getProductRepo().getListProducts().get(i));
                return true;
            }
        }
        System.out.println("Produkt nicht verfügbar: " + getProductRepo().getListProducts().get(i));
        return false;
    }
}
