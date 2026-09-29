package org.example;

import java.util.ArrayList;
import java.util.List;

public class ShopService {

    //Properties
    private ProductRepo productRepo;
    private OrderRepoInterface orderRepo;
    //private OrderRepoInterface orderMapRepo;

    //Constructor
    public ShopService(OrderRepoInterface orderRepo) {
        this.productRepo = new ProductRepo();
        this.orderRepo = orderRepo;
    }

    //Getter und Setter
    public ProductRepo getProductRepo() {
        return productRepo;
    }

    public void setProductRepo(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public OrderRepoInterface getOrderRepo() {
        return orderRepo;
    }

    public void setOrderRepo(OrderRepoInterface orderRepo) {
        this.orderRepo = orderRepo;
    }

    //Methods
    //Schritt 1: Implementiere eine Methode zum Aufgeben einer neuen Bestellung.
    // Die Artikel werden später unter Angabe der Produkt Id bestellt.

    public void placeOrder(String orderId) {
        //hol die Bestellung mit der ID von d OrderRepoList
        Order tmpOrder = getOrderRepo().getById(orderId);

        if (tmpOrder == null) {
            return;
        }

        for (Product product : tmpOrder.product()) {
            isAvailable(product.id());
        }
    }

    //Schritt 2: Prüfe, ob die bestellten Produkte existieren.
    // Wenn nicht, gib eine System.out.println-Nachricht aus.
    public boolean isAvailable(String productId) {
        //iteriere durch die Produkte und guck mal ob sie in Produktliste existieren
        for (Product product : getProductRepo().getListProducts()) {
            if (product.id().equals(productId)) {
                System.out.println("Produkt verfügbar: " + product);
                return true;
            } else {
                System.out.println("Weiter suchen");
            }
        }
        System.out.println("Produkt nicht verfügbar: " + productId);
        return false;
    }

    @Override
    public String toString() {
        return "ShopService{" +
                "productRepo=" + productRepo +
                ", orderRepo=" + orderRepo +
                '}';
    }
}
