package be.drissamri.performance;

import be.drissamri.performance.services.ProductService;
import com.google.gson.Gson;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DaggerAwsLambdaModule.class})
public interface AwsLambdaComponent {

    ProductService productService();

    Gson gson();


}
