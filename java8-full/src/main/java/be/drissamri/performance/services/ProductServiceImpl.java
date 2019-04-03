package be.drissamri.performance.services;


import be.drissamri.performance.model.Product;
import be.drissamri.performance.model.ProductRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProductServiceImpl implements ProductService {
    static final Logger log = LogManager.getLogger(ProductServiceImpl.class);

    private final DynamoDbClient dynamoDbClient;

    @Inject
    public ProductServiceImpl(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    @Override
    public Product addProduct(final ProductRequest productRequest) {
        final String productId = UUID.randomUUID().toString();

        final Map<String, AttributeValue> itemValues = new HashMap<>();
        itemValues.put("id", AttributeValue.builder().s(productId).build());
        itemValues.put("name", AttributeValue.builder().s(productRequest.getName()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName("products")
                .item(itemValues)
                .build();

        try {
            long startTime = System.currentTimeMillis();
            this.dynamoDbClient.putItem(request);
            long endTime = System.currentTimeMillis();
            log.info("DynamoDB: " + (endTime - startTime) + " milliseconds");

            return new Product(productId, productRequest.getName());
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new RuntimeException("Database update failed!");
        }
    }
}
