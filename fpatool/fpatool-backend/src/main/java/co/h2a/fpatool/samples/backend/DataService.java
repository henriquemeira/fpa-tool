package co.h2a.fpatool.samples.backend;

import java.io.Serializable;
import java.util.Collection;

import co.h2a.fpatool.samples.backend.data.Category;
import co.h2a.fpatool.samples.backend.data.Product;
import co.h2a.fpatool.samples.backend.mock.MockDataService;

/**
 * Back-end service interface for retrieving and updating product data.
 */
public abstract class DataService implements Serializable {

    public abstract Collection<Product> getAllProducts();

    public abstract Collection<Category> getAllCategories();

    public abstract void updateProduct(Product p);

    public abstract void deleteProduct(int productId);

    public abstract Product getProductById(int productId);

    public static DataService get() {
        return MockDataService.getInstance();
    }

}
