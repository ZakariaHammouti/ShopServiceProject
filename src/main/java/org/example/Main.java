package org.example;


import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.util.*;
import java.util.UUID;

public class Main {
    static void main(String[] args) throws IOException {

        CsvReader csvReader = new CsvReader();
        List<String[]> tmpList = csvReader.getLines();

        OrderRepoInterface orderMapRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(orderMapRepo, tmpList, Main.IdService());

        //System.out.println(shopService.getProductRepo().getListProducts());
        int size = shopService.getProductRepo().getListProducts().size();
        System.out.println("size" + size);

        for (int i = 0; i < size; i++) {
            System.out.println(shopService.getProductRepo().getListProducts().get(i));
        }
        System.out.println("");


        List<Product> orderProducts = new ArrayList<>() {
        };
        List<Product> productsToCheckBeforeOrder = new ArrayList<>() {
        };

        //Random random = new Random();
        //UUID uuid = UUID.randomUUID();
        // int randomOrderId = random.nextInt(10001) + 10000;//[10000, 20000]
        // String randomOrderIdStr = uuid.toString();

        Order orderFromConsole;

        Scanner scanner = new Scanner(System.in);
        String givenProduct = "";
        while (true) {
            System.out.println("Template created by KI");
            System.out.println("""
                    ╔══════════════════════════════════════════════╗
                    ║              🛒 SHOP SERVICE                 ║
                    ╠══════════════════════════════════════════════╣
                    ║ PRODUKTE AUSWÄHLEN                           ║
                    ║                                              ║
                    ║ productId, quantity                          ║
                    ║ Beispiel: 1, 2  Enter                                                              ║
                    ╠══════════════════════════════════════════════╣
                    ║ BESTELLUNG                                   ║
                    ║                                              ║
                    ║ a  → Bestellung abbrechen                    ║
                    ║ b  → Bestellung in Bestellungsliste einfügen ║
                    ╠══════════════════════════════════════════════╣
                    ║ WARENEINGANG                                 ║
                    ║                                              ║
                    ║ c, productId, quantity                       ║
                    ║ Beispiel: c, 1, 22 ENTER                     ║
                    ║      und Order ID vergeben                                                      
                                                                   ║
                    ╠══════════════════════════════════════════════╣
                    ║ BESTELLUNGEN                                 ║
                    ║                                              ║
                    ║ d  → Bestellungen "Nach Status sortiert"  anzeigen ║ 
                    ║ f, orderId → Bestellung versenden            ║
                    ║ g, orderId → Bestellung abschließen          ║
                    ╚══════════════════════════════════════════════╝
                    """);
            givenProduct = scanner.nextLine();
            if (givenProduct.equals("b")) {
                if (orderProducts != null) {
                    if (!orderProducts.isEmpty()) {
                        UUID uuid = UUID.randomUUID();
                        String randomOrderIdStr = uuid.toString();
                        orderFromConsole = new Order(randomOrderIdStr, orderProducts);
                        shopService.getOrderRepo().addOrder(orderFromConsole);
                        orderProducts = new ArrayList<>();
                    }
                }
            }
            if (givenProduct.equals("a")) {
                break;
            }
            if (givenProduct.startsWith("c")) {
                //add Products to ProductRepo
                System.out.println("Waren Eingang");
                String[] productsAddToStock = givenProduct.split(",");
                //Lösche c
                productsAddToStock = Arrays.copyOfRange(productsAddToStock, 1, productsAddToStock.length);
                Map<String, Integer> mapNewProducts = new HashMap<>();
                //Damit ich paare bilden kann
                int length = productsAddToStock.length;
                if (length % 2 == 0) {
                    for (int i = 0; i < productsAddToStock.length; i = i + 2) {
                        System.out.println(productsAddToStock[i]);
                        //Change quantity of Product in list of products
                        //Ungerade stelle sind Ids
                        mapNewProducts.put(productsAddToStock[i], Integer.parseInt(productsAddToStock[i + 1]));
                    }
                }
                if (mapNewProducts != null) {
                    shopService.getProductRepo().getNewProductsDelivered(mapNewProducts);
                    System.out.println("GET List After Updating: " +
                            shopService.getProductRepo().getAllProducts());
                }
            }
            if (givenProduct.startsWith("d")) {
                System.out.println("In Bearbeitung:");
                shopService.getOrderRepo().getAll().stream()
                        .filter(order -> order.status() == OrderStatus.PROCESSING).forEach(System.out::println);
               /* System.out.println("OrderList: " + shopService.getOrderRepo().getAll().stream()
                        .map(order -> order.product()).toList());*/
                System.out.println("Versendet:");
                shopService.getOrderRepo().getAll().stream()
                        .filter(order -> order.status() == OrderStatus.IN_DELIVERY).forEach(System.out::println);

                System.out.println("Abgeschlossen:");
                shopService.getOrderRepo().getAll().stream()
                        .filter(order -> order.status() == OrderStatus.COMPLETED).forEach(System.out::println);
            }
            if (givenProduct.startsWith("f")) {
                System.out.println("Order raussuchen und PlaceOrder starten");
                String[] getOrderIdFromConsole = givenProduct.split(",");
                try {
                    shopService.placeOrder(getOrderIdFromConsole[1].trim());
                } catch (OrderNotFound e) {
                    System.out.println("Order Id Not Found");
                }
            }
            if (givenProduct.startsWith("g")) {
                System.out.println("Order auf completed setzen");
                String[] getOrderIdFromConsole = givenProduct.split(",");
                try {
                    if (shopService.getOrderRepo().getById(getOrderIdFromConsole[1].trim()).status().equals(OrderStatus.IN_DELIVERY)) {
                        shopService.updateOrderStatus(getOrderIdFromConsole[1].trim(), OrderStatus.COMPLETED);
                    }
                } catch (OrderNotFound e) {
                    System.out.println("Order Id Not Found");
                }
            }

            // Produkte einfügen in temporäre Liste
            else {
                String[] productIdAndCount = givenProduct.split(",");
                if (productIdAndCount.length >= 2) {
                    //Erstelle Product
                    Optional<Product> result = shopService.getProductRepo().getProduct(productIdAndCount[0].trim());
                    if (result.isPresent()) {
                        Product productToCheck = result.get();
                        //Menge Checken
                        if (Integer.parseInt(productIdAndCount[1].trim()) > productToCheck.quantity()) {
                            System.out.println("Menge zu Groß. Verfügbare Menge: " + productToCheck.quantity());

                        } else {
                            System.out.println("Menge passt");
                            for (int i = 0; i < Integer.parseInt(productIdAndCount[1].trim()); i++) {
                                orderProducts.add(productToCheck);
                            }
                        }
                    } else {
                        System.out.println("Product nicht gefunden");
                    }

                }
            }
            System.out.println("Menge eingeben und enter: ");
        }
    }

    public static String IdService() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
