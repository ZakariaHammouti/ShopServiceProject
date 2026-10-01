package org.example;

import java.util.*;

public class ProductRepo {

    //Properties
    // Produkte Erstellen
  /*  private Product product1 = new Product("Phone", "15", 253.75, true, 17);
    private Product product2 = new Product("Laptop", "20", 445.69, true, 2);
    private Product product3 = new Product("SmartWatch", "87", 385.39, true, 3);
    private Product product4 = new Product("Table", "12", 55.99, true, 12);
    private Product product5 = new Product("Headset", "35", 15.15, true, 1);
    private Product product6 = new Product("Phone_Samsung", "45", 353.75, true, 1);
    private Product product7 = new Product("Laptop_Dell", "22", 745.69, true, 1);
    private Product product8 = new Product("Smart_TV", "17", 354.39, false, 0);
    private Product product9 = new Product("USBStick", "42", 12.99, false, 0);
    private Product product10 = new Product("AirFryer", "65", 100.15, false, 0);
*/
    private List<Product> listProducts = new ArrayList<>();
    ;

    public ProductRepo() {

    }

    public ProductRepo(List<String[]> listProducts) {

        createListProducts(listProducts);
        /*   this.listProducts = new ArrayList<>();
        this.addProduct(product1);
        this.addProduct(product2);
        this.addProduct(product3);
        this.addProduct(product4);
        this.addProduct(product5);
        this.addProduct(product6);
        this.addProduct(product7);
        this.addProduct(product8);
        this.addProduct(product9);
        this.addProduct(product10);*/
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

    public Product getProduct(String productId) {

        for (Product product : getListProducts()) {
            if (productId.equals(product.id())) {
                return product;
            }
        }
        return null;
        //return listProducts.get(Integer.parseInt(productId));
        //return new Product("", "", 4.7, false);
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
        System.out.println("Nach Update: " +
                getListProducts().get(index));
    }

    public void getNewProductsDelivered(Map<String, Integer> mapNewProducts) {
        //key = product id und value = quantity
        System.out.println("MAPPPPPPPPP: " + mapNewProducts.size());

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

    public List<Product> getAllProducts() {
        return this.listProducts;
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
