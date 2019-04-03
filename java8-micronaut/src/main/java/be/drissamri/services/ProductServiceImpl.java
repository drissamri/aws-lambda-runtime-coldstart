package be.drissamri.services;

import be.drissamri.ProductController;
import be.drissamri.model.Product;
import be.drissamri.model.ProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProductServiceImpl implements ProductService {
    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

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
            LOG.info("DynamoDB: " + (endTime - startTime) + " milliseconds");

            return new Product(productId, productRequest.getName());
        } catch (Exception e) {
            LOG.warn(e.getMessage());
            throw new RuntimeException("Database update failed!");
        }
    }
}
