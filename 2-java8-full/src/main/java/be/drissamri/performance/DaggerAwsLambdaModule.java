package be.drissamri.performance;

import be.drissamri.performance.services.ProductService;
import be.drissamri.performance.services.ProductServiceImpl;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import javax.inject.Singleton;

@Module
public class DaggerAwsLambdaModule {
    static final Logger log = LogManager.getLogger(DaggerAwsLambdaModule.class);

    @Provides
    @Singleton
    public DynamoDbClient dynamoDB() {
        long startTime = System.currentTimeMillis();
        final DynamoDbClient dynamoDbClient = DynamoDbClient.builder()
                .httpClientBuilder(UrlConnectionHttpClient.builder())
                .region(Region.EU_WEST_1)
                .build();
        long endTime = System.currentTimeMillis();
        log.info("INIT DynamoDbClient: " + ( endTime - startTime) + " milliseconds");
        return dynamoDbClient;
    }

    @Provides
    @Singleton
    public ProductService productService(final DynamoDbClient dynamoDB) {
        long startTime = System.currentTimeMillis();
        final ProductServiceImpl productService = new ProductServiceImpl(dynamoDB);
        long endTime = System.currentTimeMillis();
        log.info("INIT ProductService: " + ( endTime - startTime) + " milliseconds");
        return productService;
    }

    @Provides
    @Singleton
    public Gson gson() {
        long startTime = System.currentTimeMillis();
        final Gson gson = new Gson();
        long endTime = System.currentTimeMillis();
        log.info("INIT Gson: " + ( endTime - startTime) + " milliseconds");
        return gson;
    }

}
