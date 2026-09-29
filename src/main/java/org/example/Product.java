package org.example;

public record Product(String title, String id, double price, boolean onStock) {

    public Product(String title, String id, double price) {
        this(title, id, price, false);
    }

    public Product withOnStock(boolean onStock) {
        return new Product(title, id, price, onStock);
    }

    //Kompakter Konstruktor für die Validierung des Preises
    // (darf nicht negativ sein )
    public Product {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negativ");
        }
    }
}
