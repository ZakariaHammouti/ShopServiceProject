package org.example;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void main(String[] args) throws IOException {

        CsvReader csvReader = new CsvReader();
        List<String[]> tmpList = csvReader.getLines();

        OrderRepoInterface orderMapRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(orderMapRepo, tmpList);


        //System.out.println(shopService.getProductRepo().getListProducts());
        int size = shopService.getProductRepo().getListProducts().size();
        for (int i = 0; i < 10; i++) {
            System.out.println(shopService.getProductRepo().getListProducts().get(i));
        }
        System.out.println("");
        System.out.println("Wähle von folgenden Produkten aus: ");

        List<Product> orderProducts = new ArrayList<>() {
        };

        orderProducts.add(shopService.getProductRepo().getListProducts().get(0));
        orderProducts.add(shopService.getProductRepo().getListProducts().get(1));
        orderProducts.add(shopService.getProductRepo().getListProducts().get(2));
        Order orderUser = new Order("10234", orderProducts);
        shopService.getOrderRepo().addOrder(orderUser);

        shopService.placeOrder("10234");

        /*

        List<Product> selectedProducts = new ArrayList<>();
        OrderRepoInterface orderMapRepoUser = new OrderMapRepo();
        ShopService shopServiceUser = new ShopService(orderMapRepoUser);

        Scanner scanner = new Scanner(System.in);
        String givenProduct = "";
        while (true) {
            System.out.println("Bestellen mit: b u enter: ");
            System.out.println("################");
            System.out.println("Oder id eingeben und enter: ");
            givenProduct = scanner.nextLine();

            if (shopServiceUser.getProductRepo().isProductNull(givenProduct))
                selectedProducts.add(shopServiceUser.getProductRepo().getProduct(givenProduct));

            if (givenProduct.equals("b"))
                break;
            else
                System.out.println("Das ausgewählte Produkt ist nicht verfügbar: " +
                        givenProduct);

        }

        Order orderUser = new Order("10234", selectedProducts);
        shopServiceUser.getOrderRepo().addOrder(orderUser);
        shopServiceUser.placeOrder("10234");

        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");


        //################## Erstelle ShopService mit OrderListRepo ##############
        //Interface und Shop Service zum Aufgeben einer Bestellung
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo);

        List<Product> listOfSelectedProducts1 = new ArrayList<>();
        listOfSelectedProducts1.add(shopService.getProductRepo().getProduct("15"));
        //listOfSelectedProducts1.add(shopService.getProductRepo().getProduct("35"));

        List<Product> listOfSelectedProducts2 = new ArrayList<>();

        //Prüfen bevor Produkte eingefügt werden
        if (shopService.getProductRepo().isProductNull("20")) {

            listOfSelectedProducts2.add(shopService.getProductRepo().getProduct("45"));
        }
        if (shopService.getProductRepo().isProductNull("17")) {
            listOfSelectedProducts2.add(shopService.getProductRepo().getProduct("17"));
        }
        if (shopService.getProductRepo().isProductNull("452")) {
            listOfSelectedProducts2.add(shopService.getProductRepo().getProduct("452"));
        }
        if (shopService.getProductRepo().isProductNull("35")) {
            listOfSelectedProducts2.add(shopService.getProductRepo().getProduct("35"));
        }

        //erstelle Order
        Order order1 = new Order("10234", listOfSelectedProducts1);
        Order order2 = new Order("34156", listOfSelectedProducts2);
        Order order3 = new Order("1344", listOfSelectedProducts1);
        Order order4 = new Order("35776", listOfSelectedProducts2);
        Order order5 = new Order("1274", listOfSelectedProducts1);
        Order order6 = new Order("3468", listOfSelectedProducts2);
        Order order7 = new Order("1234", listOfSelectedProducts1);
        Order order8 = new Order("3456", listOfSelectedProducts2);

        //Füge Bestellungen zu Liste hinzu
        shopService.getOrderRepo().addOrder(order1);
        shopService.getOrderRepo().addOrder(order2);


        //Bestellung mit der Id aufgeben
        shopService.getOrderRepo().removeOrder("1234");
        shopService.placeOrder("3456");

        //#################### Ende Bestellung mit OrdListRepo ###################

        //################# Erstelle ShopService mit OrderMapRepo ###############
        OrderRepoInterface orderMapRepo = new OrderMapRepo();
        ShopService shopService2 = new ShopService(orderMapRepo);

        shopService2.getOrderRepo().addOrder(order1);
        shopService2.getOrderRepo().addOrder(order2);
        shopService2.getOrderRepo().addOrder(order3);
        shopService2.getOrderRepo().addOrder(order5);
        shopService2.getOrderRepo().addOrder(order7);

        //shopService2.getOrderRepo().addOrder(order2);

        if (shopService2.placeOrder(order1.orderId())) {
            orderMapRepo.cutToHistoryList(order1);
        }
        if (shopService2.placeOrder(order3.orderId())) {
            orderMapRepo.cutToHistoryList(order3);
        }
        if (shopService2.placeOrder(order5.orderId())) {
            orderMapRepo.cutToHistoryList(order5);
        }
        if (shopService2.placeOrder(order7.orderId())) {
            orderMapRepo.cutToHistoryList(order7);
        }

        //System.out.println(orderMapRepo.);
        //Bestellungen die erfolgreich waren entfernen von der Order List
        //Und einfügen in HistoryList
*/
    }
}
