package be.drissamri;

import be.drissamri.model.Product;
import be.drissamri.model.ProductRequest;
import be.drissamri.services.ProductService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Controller("/products")
public class ProductController {
    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Inject
    private ProductService productService;

    @Post
    public Product addProduct(@Body ProductRequest request) {
        LOG.info("REQUEST: {}", request);

        return productService.addProduct(request);
    }
}