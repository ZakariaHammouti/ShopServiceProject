package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {

    @Test
    void removeProduct_ShouldNotRemoveByGivenEmptyId() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("products_100.csv"));

        List<String[]> dataList = new ArrayList<>();

        // Erste Zeile überspringen (Header)
        for (int i = 1; i < lines.size(); i++) {
            dataList.add(lines.get(i).split(","));
        }

        // ShopService mit den CSV-Daten erstellen
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo, dataList, "");

        ProductRepo repo = shopService.getProductRepo();
        repo.removeProduct("");
        assertEquals(100, repo.getListProducts().size());
    }

    @Test
    void removeProduct_ShouldRemoveByGivenAvailableProductId() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("products_100.csv"));

        List<String[]> dataList = new ArrayList<>();

        // Erste Zeile überspringen (Header)
        for (int i = 1; i < lines.size(); i++) {
            dataList.add(lines.get(i).split(","));
        }

        // ShopService mit den CSV-Daten erstellen
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo, dataList, "");

        ProductRepo repo = shopService.getProductRepo();
        repo.removeProduct("15");
        assertEquals(99, repo.getListProducts().size());
    }

    @Test
    void removeProduct_ShouldNotRemoveByGivenANullProductId() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("products_100.csv"));

        List<String[]> dataList = new ArrayList<>();

        // Erste Zeile überspringen (Header)
        for (int i = 1; i < lines.size(); i++) {
            dataList.add(lines.get(i).split(","));
        }

        // ShopService mit den CSV-Daten erstellen
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo, dataList, "");

        ProductRepo repo = shopService.getProductRepo();
        repo.removeProduct(null);
        assertEquals(100, repo.getListProducts().size());
    }

    @Test
    void removeProduct_ShouldRemoveByGivenAllProductIds() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("products_100.csv"));

        List<String[]> dataList = new ArrayList<>();

        // Erste Zeile überspringen (Header)
        for (int i = 1; i < lines.size(); i++) {
            dataList.add(lines.get(i).split(","));
        }

        // ShopService mit den CSV-Daten erstellen
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo, dataList, "");

        ProductRepo repo = shopService.getProductRepo();
        repo.removeProduct("15");
        repo.removeProduct("20");
        repo.removeProduct("87");
        repo.removeProduct("12");
        repo.removeProduct("35");
        repo.removeProduct("45");
        repo.removeProduct("22");
        repo.removeProduct("42");
        repo.removeProduct("65");
        repo.removeProduct("17");

        assertEquals(90, repo.getListProducts().size());
    }

    @Test
    void removeProduct_ShouldRemoveOneTimeByGivenSameIdMoreTimes() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("products_100.csv"));

        List<String[]> dataList = new ArrayList<>();

        // Erste Zeile überspringen (Header)
        for (int i = 1; i < lines.size(); i++) {
            dataList.add(lines.get(i).split(","));
        }

        // ShopService mit den CSV-Daten erstellen
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo, dataList, "");

        ProductRepo repo = shopService.getProductRepo();
        repo.removeProduct("15");
        repo.removeProduct("15");
        repo.removeProduct("15");
        repo.removeProduct("15");

        assertEquals(99, repo.getListProducts().size());
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void getProduct_ShouldReturnValidProductByGivenValidProductId() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("products_100.csv"));

        List<String[]> dataList = new ArrayList<>();

        // Erste Zeile überspringen (Header)
        for (int i = 1; i < lines.size(); i++) {
            dataList.add(lines.get(i).split(","));
        }

        // ShopService mit den CSV-Daten erstellen
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo, dataList, "");

        ProductRepo repo = shopService.getProductRepo();

        assertEquals(repo.getListProducts().getFirst(),
                repo.getProduct("1").get());
    }

    @Test
    void getProduct_ShouldReturnValidProductByGivenNonValidProductId() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("products_100.csv"));

        List<String[]> dataList = new ArrayList<>();

        // Erste Zeile überspringen (Header)
        for (int i = 1; i < lines.size(); i++) {
            dataList.add(lines.get(i).split(","));
        }

        // ShopService mit den CSV-Daten erstellen
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo, dataList, "");

        ProductRepo repo = shopService.getProductRepo();

        assertNotEquals(repo.getListProducts().getFirst(),
                repo.getProduct("2"));
    }

    @Test
    void getProduct_ShouldReturnValidProductByGivenEmptyProductId() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("products_100.csv"));

        List<String[]> dataList = new ArrayList<>();

        // Erste Zeile überspringen (Header)
        for (int i = 1; i < lines.size(); i++) {
            dataList.add(lines.get(i).split(","));
        }

        // ShopService mit den CSV-Daten erstellen
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo, dataList, "");

        ProductRepo repo = shopService.getProductRepo();

        assertNotEquals(repo.getListProducts().getFirst(),
                repo.getProduct(""));
    }

    @Test
    void getProduct_ShouldReturnValidProductByGivenValidProductIdByListEmpty() {
        ProductRepo repo = new ProductRepo();
        repo.getListProducts().clear();
        assertEquals(Optional.empty(), repo.getProduct("15"));
    }

    @Test
    void getProduct_ShouldReturnValidProductByGivenValidProductIdByList() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("products_100.csv"));

        List<String[]> dataList = new ArrayList<>();

        // Erste Zeile überspringen (Header)
        for (int i = 1; i < lines.size(); i++) {
            dataList.add(lines.get(i).split(","));
        }

        // ShopService mit den CSV-Daten erstellen
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo, dataList, "");

        ProductRepo repo = shopService.getProductRepo();
        assertNotNull(repo.getProduct("15"));
    }


    @Test
    void updateStatusOfProduct_CheckUpdatingByGivenZero() {
        ProductRepo repo = new ProductRepo();

    }

    @Test
    void updateStatusOfProduct_CheckUpdatingQuantityByProductsWithOneQuantities() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("products_100.csv"));

        List<String[]> dataList = new ArrayList<>();

        // Erste Zeile überspringen (Header)
        for (int i = 1; i < lines.size(); i++) {
            dataList.add(lines.get(i).split(","));
        }

        // ShopService mit den CSV-Daten erstellen
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo, dataList, "");

        ProductRepo repo = shopService.getProductRepo();

        int actualQuantity = repo.getListProducts().get(4).quantity();
        repo.updateStatusOfProduct(4);

        assertEquals(actualQuantity - 1,
                repo.getListProducts().get(4).quantity());
    }

    @Test
    void updateStatusOfProduct_CheckUpdatingQuantityByProductsWithQuantitiesGreaterZero() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("products_100.csv"));

        List<String[]> dataList = new ArrayList<>();

        // Erste Zeile überspringen (Header)
        for (int i = 1; i < lines.size(); i++) {
            dataList.add(lines.get(i).split(","));
        }

        // ShopService mit den CSV-Daten erstellen
        OrderRepoInterface orderListRepo = new OrderListRepo();
        ShopService shopService = new ShopService(orderListRepo, dataList, "");

        ProductRepo repo = shopService.getProductRepo();
        int actualQuantity = repo.getListProducts().get(0).quantity();
        repo.updateStatusOfProduct(0);

        assertEquals(actualQuantity - 1,
                repo.getListProducts().get(0).quantity());
    }
}
