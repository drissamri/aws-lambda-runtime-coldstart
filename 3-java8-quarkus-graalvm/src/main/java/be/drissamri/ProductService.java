package be.drissamri;

import be.drissamri.model.Product;
import be.drissamri.model.ProductRequest;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class ProductService {
    private static final Logger log = Logger.getLogger(ProductService.class);
    //  private final DynamoDbClient dynamoDbClient;

    public ProductService() {
        /*
      this.dynamoDbClient = DynamoDbClient.builder()
                .region(Region.EU_WEST_1)
                .build();
         */
    }

    public Product addProduct(final ProductRequest productRequest) {
        final String productId = UUID.randomUUID().toString();
        return new Product(productId, productRequest.getName());

        /*
        final Map<String, AttributeValue> itemValues = new HashMap<>();

        itemValues.put("id", AttributeValue.builder().s(productId).build());
        itemValues.put("name", AttributeValue.builder().s(productRequest.getName()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName("quarkus-native-products")
                .item(itemValues)
                .build();

        try {
            long startTime = System.currentTimeMillis();
            //this.dynamoDbClient.putItem(request);
            long endTime = System.currentTimeMillis();
            log.info("DynamoDB: " + (endTime - startTime) + " milliseconds");

            return new Product(productId, productRequest.getName());
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new RuntimeException("Database update failed!");
        }

         */
    }
}
