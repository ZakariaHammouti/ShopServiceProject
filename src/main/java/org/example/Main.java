package org.example;


import java.io.IOException;
import java.util.*;

public class Main {
    static void main(String[] args) throws IOException {

        CsvReader csvReader = new CsvReader();
        List<String[]> tmpList = csvReader.getLines();

        OrderRepoInterface orderMapRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(orderMapRepo, tmpList);

        //System.out.println(shopService.getProductRepo().getListProducts());
        int size = shopService.getProductRepo().getListProducts().size();
        for (int i = 0; i < 20; i++) {
            System.out.println(shopService.getProductRepo().getListProducts().get(i));
        }
        System.out.println("");
        System.out.println("Wähle von folgenden Produkten aus: ");

        List<Product> orderProducts = new ArrayList<>() {
        };
        List<Product> productsToCheckBeforeOrder = new ArrayList<>() {
        };

        Random random = new Random();
        int randomOrderId = random.nextInt(10001) + 10000;//[10000, 20000]
        String randomOrderIdStr = String.valueOf(randomOrderId);

        Order orderFromConsole;

        Scanner scanner = new Scanner(System.in);
        String givenProduct = "";
        while (true) {
            System.out.println("Abbrechen mit: a und enter: ");
            System.out.println("Bestellen mit: b und enter: ");
            System.out.println("WarenEingang mit c, d: gefolgt von Product Id und Menge mit Komma dazwischen und enter: ");
            System.out.println("################");
            System.out.println("Oder product id eingeben und Menge mit Komma " +
                    "dazwischen dann enter: ");
            givenProduct = scanner.nextLine();
            if (givenProduct.equals("b")) {
                if (orderProducts != null) {
                    if (!orderProducts.isEmpty()) {
                        orderFromConsole = new Order(randomOrderIdStr, orderProducts);
                        shopService.getOrderRepo().addOrder(orderFromConsole);
                        shopService.placeOrder(orderFromConsole.orderId());
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
            // Produkte einfügen in Order
            else {
                String[] productIdAndCount = givenProduct.split(",");
                if (productIdAndCount.length >= 2) {
                    //Erstelle Product
                    Product productToCheck = shopService.getProductRepo().getProduct(productIdAndCount[0].trim());
                    //Menge Checken
                    if (Integer.parseInt(productIdAndCount[1].trim()) > productToCheck.quantity()) {
                        System.out.println("Menge zu Groß. Verfügbare Menge: " + productToCheck.quantity());

                    } else {
                        System.out.println("Menge passt");
                        for (int i = 0; i < Integer.parseInt(productIdAndCount[1].trim()); i++) {
                            orderProducts.add(productToCheck);
                        }
                    }

                }
            }
            System.out.println("Menge eingeben und enter: ");
        }
    }
}
