package by.beg.catalog.service;

import by.beg.catalog.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductService {

    private ArrayList<Product> productList;

    @Autowired
    public ProductService(@Qualifier("productList") ArrayList<Product> productList) {
        this.productList = productList;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void removeProduct(int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                productList.remove(product);
                return;
            }
        }
    }

    public Product searchProductById(int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public ArrayList<Product> filterProducts(String string) {
        ArrayList<Product> filterList = new ArrayList<>();

        for (Product currentProduct : productList) {

            String name = currentProduct.getName().toLowerCase();
            String id = Integer.toString(currentProduct.getId());

            if (name.startsWith(string.toLowerCase())) {
                filterList.add(currentProduct);
            } else if (id.startsWith(string.toLowerCase())) {
                filterList.add(currentProduct);
            }

        }

        return filterList;
    }

    public void editProduct(Product oldProduct, Product newProduct) {
        oldProduct.setName(newProduct.getName());
        oldProduct.setType(newProduct.getType());
        oldProduct.setDescription(newProduct.getDescription());
        oldProduct.setPrice(newProduct.getPrice());
    }


    public void orderById() {
        productList.sort((p1, p2) -> p1.getId() - p2.getId());
    }

    public void orderByName() {
        productList.sort((Product p1, Product p2) -> {
            if (p1.getName().compareTo(p2.getName()) != 0) {
                return p1.getName().compareTo(p2.getName());
            } else {
                return p1.getId() - p2.getId();
            }
        });
    }

    public void orderByPrice() {
        productList.sort((Product p1, Product p2) -> {
            if (p1.getPrice() - p2.getPrice() != 0) {
                return p1.getPrice() - p2.getPrice();
            } else {
                return p1.getId() - p2.getId();
            }
        });
    }

    public void orderByType() {
        productList.sort((Product p1, Product p2) -> {
            if (p1.getType().getName().compareTo(p2.getType().getName()) != 0) {
                return p1.getType().getName().compareTo(p2.getType().getName());
            } else {
                return p1.getId() - p2.getId();
            }
        });
    }

}
