package be.drissamri.performance.services;

import be.drissamri.performance.model.Product;
import be.drissamri.performance.model.ProductRequest;

public interface ProductService {
    Product addProduct(ProductRequest product);
}
