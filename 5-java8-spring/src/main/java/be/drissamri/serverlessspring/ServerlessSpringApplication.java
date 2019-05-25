package be.drissamri.serverlessspring;

import be.drissamri.serverlessspring.model.Product;
import be.drissamri.serverlessspring.model.ProductRequest;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.function.context.FunctionRegistration;
import org.springframework.cloud.function.context.FunctionType;
import org.springframework.cloud.function.context.FunctionalSpringApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;

import java.util.UUID;
import java.util.function.Function;


@SpringBootConfiguration
public class ServerlessSpringApplication
        implements ApplicationContextInitializer<GenericApplicationContext> {

    public static void main(String[] args) {
        FunctionalSpringApplication.run(ServerlessSpringApplication.class, args);
    }

    @Bean
    public Function<ProductRequest, Product> addProduct() {
        return value -> {
            final Product product = new Product(UUID.randomUUID().toString(), value.getName());
            return product;
        };
    }

    @Override
    public void initialize(GenericApplicationContext context) {
        context.registerBean("addProduct", FunctionRegistration.class,
                () -> new FunctionRegistration<>(addProduct())
                        .type(FunctionType.from(ProductRequest.class).to(Product.class)));
    }
}