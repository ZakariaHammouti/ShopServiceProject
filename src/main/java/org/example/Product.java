package org.example;

public record Product(String title, String id, double price, boolean onStock) {

    public Product(String title, String id, double price) {
        this(title, id, price, false);
    }
}
