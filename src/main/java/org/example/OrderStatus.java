package org.example;

//Bestellstatus
public enum OrderStatus {
    PROCESSING("In Bearbeitung"),
    IN_DELIVERY("Versendet"),
    COMPLETED("Abgeschlossen");

    private final String statusDescription;

    OrderStatus(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "statusDescription='" + statusDescription + '\'' +
                '}';
    }
}







