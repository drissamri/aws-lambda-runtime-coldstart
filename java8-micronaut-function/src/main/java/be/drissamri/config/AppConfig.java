package be.drissamri.config;

import io.micronaut.context.annotation.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import javax.inject.Singleton;

@Factory
public class AppConfig {
    private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);

    @Singleton
    public DynamoDbClient dynamoDbClient() {
        long startTime = System.currentTimeMillis();
        final DynamoDbClient dynamoDbClient = DynamoDbClient.builder()
                .httpClientBuilder(UrlConnectionHttpClient.builder())
                .region(Region.EU_WEST_1)
                .build();
        long endTime = System.currentTimeMillis();
        LOG.info("INIT DynamoDbClient: " + (endTime - startTime) + " milliseconds");
        return dynamoDbClient;
    }
}
