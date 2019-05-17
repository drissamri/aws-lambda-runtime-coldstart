package be.drissamri;

import be.drissamri.model.Product;
import be.drissamri.model.ProductRequest;
import be.drissamri.services.ProductService;
import io.micronaut.function.executor.FunctionInitializer;
import io.micronaut.http.annotation.Body;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;

@Singleton
public class AddProductFunction extends FunctionInitializer {
    private static final Logger LOG = LoggerFactory.getLogger(AddProductFunction.class);

    @Inject
    private ProductService productService;

    public Product execute(@Body ProductRequest request) {
        LOG.info("REQUEST: " + request);
        System.out.println(request.getName());
         return productService.addProduct(request);
    }

    /**
     * This main method allows running the function as a CLI application using: echo '{}' | java -jar function.jar
     * where the argument to echo is the JSON to be parsed.
     */
    public static void main(String...args) throws IOException {
        AddProductFunction function = new AddProductFunction();
        function.run(args, (context)-> function.execute(context.get(ProductRequest.class)));
    }
}

