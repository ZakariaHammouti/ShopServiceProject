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

        List<Product> products = tmpOrder.product();
        System.out.println("Products to place: " + products.size());

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (isAvailable(product.id())) {
                products.set(i, product.withOnStock(true));
            }
        }
    }

    //Schritt 2: Prüfe, ob die bestellten Produkte existieren.
    // Wenn nicht, gib eine System.out.println-Nachricht aus.
    public boolean isAvailable(String productId) {
        //iteriere durch die Produkte und guck mal ob sie in Produktliste existieren
        //for (Product product : getProductRepo().getListProducts()) {
        for (int i = 0; i < getProductRepo().getListProducts().size(); i++) {
            if (getProductRepo().getListProducts().get(i).id().equals(productId)) {
                if (getProductRepo().getListProducts().get(i).quantity() > 0) {
                    // update prodRepoList quantity != 0
                    System.out.println("Das Produkt mit der Id: "
                            + getProductRepo().getListProducts().get(i) +
                            "ist auf Lager");
                    getProductRepo().updateStatusOfProduct(i);
                    return true;
                } else {
                    // quantity = 0
                    System.out.println("Das Produkt mit der Id: "
                            + getProductRepo().getListProducts().get(i) +
                            " ist nicht auf Lager");
                    return false;
                }
            }
        }
        System.out.println("Produkt mit der ID" +
                productId + " ist nicht verfügbar: ");
        return false;
    }

    //Ich will die Anzahl der Produkte in meier Produkteliste wissen


    @Override
    public String toString() {
        return "ShopService{" +
                "productRepo=" + productRepo +
                ", orderRepo=" + orderRepo +
                '}';
    }
}
