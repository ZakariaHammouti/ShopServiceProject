package org.example;

public record Product(String title, String id, double price, boolean onStock, int quantity) {

   /* public Product(String title, String id, double price, boolean onStock, int quantity) {
        this(title, id, price, false, quantity);
    }*/

    public Product withOnStock(boolean onStock) {
        return new Product(title, id, price, onStock, quantity);
    }

    public Product withQuantity(int quantity) {
        return new Product(title, id, price, onStock, quantity);
    }

    //Kompakter Konstruktor für die Validierung des Preises
    // (darf nicht negativ sein )
    public Product {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negativ");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Price cannot be negativ");
        }
    }
}
