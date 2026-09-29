package org.example;

import java.util.List;

public class ShopService {

    //Properties
    private ProductRepo productRepo;
    OrderRepoInterface orderRepoInterface = new OrderListRepo();

    //Constructor
    public ShopService(OrderRepoInterface orderRepoInterface) {
        this.productRepo = new ProductRepo();
    }

    //Getter und Setter
    public ProductRepo getProductRepo() {
        return productRepo;
    }

    public void setProductRepo(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    //Methods
    //Schritt 1: Implementiere eine Methode zum Aufgeben einer neuen Bestellung.
    // Die Artikel werden später unter Angabe der Produkt Id bestellt.
    public void placeOrder(int productId) {
        //add to list or add to map
    }


    //Schritt 2: Prüfe, ob die bestellten Produkte existieren.
    // Wenn nicht, gib eine System.out.println-Nachricht aus.
    public boolean isAvailable(List<Order> listOrder) {

        return false;
    }
}
