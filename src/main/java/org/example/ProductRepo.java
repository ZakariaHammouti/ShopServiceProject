package org.example;

import java.util.*;

public class ProductRepo {

    //Properties
    private List<Product> listProducts = new ArrayList<>();
    ;

    public ProductRepo() {

    }

    public ProductRepo(List<String[]> listProducts) {

        createListProducts(listProducts);
    }

    //Getter & Setter
    public List<Product> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }

    private void createListProducts(List<String[]> listAvailableProducts) {

        Random random = new Random();
        int i = 1;
        for (String[] product : listAvailableProducts) {
            Product productTmp = new Product(product[1],
                    String.valueOf(i),
                    Double.parseDouble(product[2]),
                    true,
                    random.nextInt(11) + 20);
            this.getListProducts().add(productTmp);
            i++;
        }

    }

    //Methods
    public void addProduct(Product product) {
        if (product != null)
            this.listProducts.add(product);
    }

    public void removeProduct(String productId) {
        if (getListProducts() != null) {
            for (int i = 0; i < getListProducts().size(); i++) {
                if (getListProducts().get(i).id().equals(productId)) {
                    getListProducts().remove(i);
                }
            }
        }
    }

    public Optional<Product> getProduct(String productId) {

        for (Product product : getListProducts()) {
            if (productId.equals(product.id())) {
                return Optional.of(product);
            }
        }

        return Optional.empty();
    }

    public boolean isProductNull(String productId) {

        return getProduct(productId) != null;
    }

    public void updateStatusOfProduct(int index) {
        int actualQuantity = getListProducts().get(index).quantity();
        Product product = getListProducts().get(index);

        Product updatedProduct = getListProducts().get(index).withQuantity((actualQuantity - 1));
        getListProducts().set(index, updatedProduct);
        if (updatedProduct.quantity() == 0) {
            updatedProduct = updatedProduct.withOnStock(false);
            getListProducts().set(index, updatedProduct);
        }
    }

    public void getNewProductsDelivered(Map<String, Integer> mapNewProducts) {
        //key = product id und value = quantity
        for (Map.Entry<String, Integer> entry : mapNewProducts.entrySet()) {

            String productId = entry.getKey();
            int newQuantity = entry.getValue();

            for (int i = 0; i < getListProducts().size(); i++) {

                Product product = getListProducts().get(i);

                if (product.id().equals(productId)) {

                    Product updatedProduct = product.withQuantity(getListProducts().get(i).quantity() + newQuantity);

                    getListProducts().set(i, updatedProduct);
                }
            }
        }
    }

    public Optional<List<Product>> getAllProducts() {

        if (this.listProducts.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(this.listProducts);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductRepo that = (ProductRepo) o;
        return Objects.equals(listProducts, that.listProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(listProducts);
    }

    @Override
    public String toString() {
        return "ProductRepo{" +
                "listProducts=" + listProducts +
                '}';
    }
}
