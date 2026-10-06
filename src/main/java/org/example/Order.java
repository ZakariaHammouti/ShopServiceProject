package org.example;

import lombok.With;

import java.util.List;

public record Order(String orderId, List<Product> product,
                    @With
                    OrderStatus status) {
    // Eine Bestellung enthält Produkte

    // Konstruktor ohne Status
    public Order(String orderId, List<Product> product) {
        this(orderId,
                product,
                OrderStatus.PROCESSING);
    }
}
