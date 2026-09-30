package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {

    @Test
    void removeProduct_ShouldNotRemoveByGivenEmptyId() {
        ProductRepo repo = new ProductRepo();
        repo.removeProduct("");
        assertEquals(10, repo.getListProducts().size());
    }

    @Test
    void removeProduct_ShouldRemoveByGivenAvailableProductId() {
        ProductRepo repo = new ProductRepo();
        repo.removeProduct("15");
        assertEquals(9, repo.getListProducts().size());
    }

    @Test
    void removeProduct_ShouldNotRemoveByGivenANullProductId() {
        ProductRepo repo = new ProductRepo();
        repo.removeProduct(null);
        assertEquals(10, repo.getListProducts().size());
    }

    @Test
    void removeProduct_ShouldNotRemoveByGivenAllProductIds() {
        ProductRepo repo = new ProductRepo();
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

        assertEquals(0, repo.getListProducts().size());
    }

    @Test
    void removeProduct_ShouldRemoveOneTimeByGivenSameIdMoreTimes() {
        ProductRepo repo = new ProductRepo();
        repo.removeProduct("15");
        repo.removeProduct("15");
        repo.removeProduct("15");
        repo.removeProduct("15");

        assertEquals(9, repo.getListProducts().size());
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void getProduct_ShouldReturnValidProductByGivenValidProductId() {
        ProductRepo repo = new ProductRepo();

        assertEquals(repo.getListProducts().getFirst(),
                repo.getProduct("15"));
    }

    @Test
    void getProduct_ShouldReturnValidProductByGivenNonValidProductId() {
        ProductRepo repo = new ProductRepo();

        assertNotEquals(repo.getListProducts().getFirst(),
                repo.getProduct("16"));
    }

    @Test
    void getProduct_ShouldReturnValidProductByGivenEmptyProductId() {
        ProductRepo repo = new ProductRepo();

        assertNotEquals(repo.getListProducts().getFirst(),
                repo.getProduct(""));
    }

    @Test
    void getProduct_ShouldReturnValidProductByGivenValidProductIdByListEmpty() {
        ProductRepo repo = new ProductRepo();
        repo.getListProducts().clear();
        assertNull(repo.getProduct("15"));
    }

    @Test
    void getProduct_ShouldReturnValidProductByGivenValidProductIdByList() {
        ProductRepo repo = new ProductRepo();
        assertNotNull(repo.getProduct("15"));
    }


    @Test
    void updateStatusOfProduct_CheckUpdatingByGivenZero() {
        ProductRepo repo = new ProductRepo();

    }

    @Test
    void updateStatusOfProduct_CheckUpdatingQuantityByProductsWithOneQuantities() {
        ProductRepo repo = new ProductRepo();
        int actualQuantity = repo.getListProducts().get(4).quantity();
        repo.updateStatusOfProduct(4);

        assertEquals(0,
                repo.getListProducts().get(4).quantity());
    }

    @Test
    void updateStatusOfProduct_CheckUpdatingQuantityByProductsWithQuantitiesGreaterZero() {
        ProductRepo repo = new ProductRepo();
        int actualQuantity = repo.getListProducts().get(0).quantity();
        repo.updateStatusOfProduct(0);

        assertEquals(actualQuantity - 1,
                repo.getListProducts().get(0).quantity());
    }
}
