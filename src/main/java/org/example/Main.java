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
            System.out.println("Wähle Produkte einzeln aus mit: produktId, quantity");
            System.out.println("Beispiel: productId1, quantity1 Enter");
            System.out.println("#####################################");
            System.out.println("a: Abbrechen und Programm beenden");
            System.out.println("b: Bestellug bestätigen");
            System.out.println("c: WarenEingang gefolgt von Product Id und Menge mit Komma dazwischen und enter: ");
            System.out.println("    Beispiel: c, productId1, quantity1, productId2, quantity2,...");
            System.out.println("#####################################");
            System.out.println("d: OrderListe ausgeben");
            System.out.println("e: Order-History-Liste ausgeben");

            givenProduct = scanner.nextLine();
            if (givenProduct.equals("b")) {
                if (orderProducts != null) {
                    if (!orderProducts.isEmpty()) {
                        UUID uuid = UUID.randomUUID();
                        String randomOrderIdStr = uuid.toString();
                        orderFromConsole = new Order(randomOrderIdStr, orderProducts);
                        shopService.getOrderRepo().addOrder(orderFromConsole);

                        try {
                            shopService.placeOrder(orderFromConsole.orderId());
                        } catch (OrderNotFound e) {
                            System.out.println("Order Id Not Found");
                        }
                        orderProducts.clear();
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
                System.out.println("OrderList: " + shopService.getOrderRepo().getAll());
            }
            if (givenProduct.startsWith("e")) {
                System.out.println("History-List: " + shopService.getOrderRepo().getAllHistory());
            }

            // Produkte einfügen in Order
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
