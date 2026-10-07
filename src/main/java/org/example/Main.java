package org.example;


import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.UUID;

public class Main {
    static void main(String[] args) throws IOException, OrderNotFound {

        String RED = "\u001B[31m";
        String YELLOW = "\u001B[33m";
        String GREEN = "\u001B[32m";
        String RESET = "\u001B[0m";

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


        List<Product> orderProducts = new ArrayList<>() {
        };
        List<Product> productsToCheckBeforeOrder = new ArrayList<>() {
        };

        //Random random = new Random();
        //UUID uuid = UUID.randomUUID();
        // int randomOrderId = random.nextInt(10001) + 10000;//[10000, 20000]
        // String randomOrderIdStr = uuid.toString();

        Order orderFromConsole;

        Scanner fileScanner = new Scanner(Path.of("Transaction.txt"));
        Scanner userScanner = new Scanner(System.in);
        String givenProduct = "";
        while (fileScanner.hasNextLine()) {


            //givenProduct = scanner.nextLine();
            givenProduct = fileScanner.nextLine().trim();

            if (givenProduct.isEmpty() || givenProduct.startsWith("#")) {
                continue;
            }

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
                System.out.println("Waren Eingang: ProduktId, Menge, ProduktId, Menge.... dann Enter...");
                String consoleInput = userScanner.nextLine();
                String[] productsAddToStock = consoleInput.split(",");
                //Lösche c
                // productsAddToStock = Arrays.copyOfRange(productsAddToStock, 1, productsAddToStock.length);
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
                System.out.println(RED + "In Bearbeitung:" + RESET);
                shopService.getOrderRepo().getAll().stream()
                        .filter(order -> order.status() == OrderStatus.PROCESSING).forEach(System.out::println);
               /* System.out.println("OrderList: " + shopService.getOrderRepo().getAll().stream()
                        .map(order -> order.product()).toList());*/
                System.out.println(YELLOW + "Versendet:" + RESET);
                shopService.getOrderRepo().getAll().stream()
                        .filter(order -> order.status() == OrderStatus.IN_DELIVERY).forEach(System.out::println);

                System.out.println(GREEN + "Abgeschlossen:" + RESET);
                shopService.getOrderRepo().getAll().stream()
                        .filter(order -> order.status() == OrderStatus.COMPLETED).forEach(System.out::println);
            }
            if (givenProduct.startsWith("f")) {
                String consoleInput = "";
                shopService.getOrderRepo().getAll().stream()
                        .filter(order -> order.status() == OrderStatus.PROCESSING)
                        .forEach(order -> System.out.
                                println(order.orderId() + "  " + order.status()));

                System.out.println("Um zu bestellen, kopiere eine Order ID dann Enter");
                if (!shopService.getOrderRepo().getAll().stream()
                        .filter(order -> order.status() == OrderStatus.PROCESSING).toList().isEmpty()) {
                    consoleInput = userScanner.nextLine();

                    //String[] getOrderIdFromConsole = givenProduct.split(",");
                    if (shopService.getOrderRepo().getById(consoleInput).status().equals(OrderStatus.PROCESSING)) {
                        try {
                            shopService.placeOrder(consoleInput.trim());
                        } catch (OrderNotFound e) {
                            System.out.println("Order Id Not Found");
                        }
                    }
                } else {
                    System.out.println(RED + "Bestellung hat nicht den Status PROCESSING, Bitte wähle von den " +
                            "vorgeschlagenen IDs aus" + RESET);
                }
            }
            if (givenProduct.startsWith("g")) {
                String consoleInput = "";
                shopService.getOrderRepo().getAll().stream()
                        .filter(order -> order.status() == OrderStatus.IN_DELIVERY)
                        .forEach(order -> System.out.
                                println(order.orderId() + "  " + order.status()));

                System.out.println("Welche Bestellungen wurden zugestellt?");
                System.out.println("kopiere eine Order ID dann Enter");
                if (!shopService.getOrderRepo().getAll().stream()
                        .filter(order -> order.status() == OrderStatus.IN_DELIVERY).toList().isEmpty()) {
                    consoleInput = userScanner.nextLine();

                    //String[] getOrderIdFromConsole = consoleInput.split(",");
                    if (shopService.getOrderRepo().getById(consoleInput).status().equals(OrderStatus.IN_DELIVERY)) {
                        try {
                            if (shopService.getOrderRepo().getById(consoleInput.trim()).status().equals(OrderStatus.IN_DELIVERY)) {
                                shopService.updateOrderStatus(consoleInput.trim(), OrderStatus.COMPLETED);
                            }
                        } catch (OrderNotFound e) {
                            System.out.println("Order Id Not Found");
                        }
                    }
                } else {
                    System.out.println(RED + "Bestellung hat nicht den Status IN_DELIVERY, Bitte wähle von den " +
                            "vorgeschlagenen IDs aus" + RESET);
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
                            System.out.println("");
                            for (int i = 0; i < Integer.parseInt(productIdAndCount[1].trim()); i++) {
                                orderProducts.add(productToCheck);
                            }
                        }
                    } else {
                        System.out.println("Product nicht gefunden");
                    }

                }
            }
            System.out.println("");
        }
    }

    public static String IdService() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
