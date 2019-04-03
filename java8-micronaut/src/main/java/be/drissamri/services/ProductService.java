package be.drissamri.services;

import be.drissamri.model.Product;
import be.drissamri.model.ProductRequest;

public interface ProductService {
    Product addProduct(ProductRequest product);
}
