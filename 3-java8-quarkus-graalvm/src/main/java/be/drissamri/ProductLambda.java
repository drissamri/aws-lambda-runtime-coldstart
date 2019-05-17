package be.drissamri;

import be.drissamri.model.Product;
import be.drissamri.model.ProductRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.jboss.logging.Logger;

import javax.inject.Inject;

public class ProductLambda implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private static final Logger log = Logger.getLogger(ProductLambda.class);

    @Inject
    ProductService productService;
    ObjectWriter writer = new ObjectMapper().writerFor(Product.class);
    ObjectReader reader = new ObjectMapper().readerFor(ProductRequest.class);

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, final Context context) {
        log.info("ProductRequest received.");

        try {
            final ProductRequest request = this.reader.readValue(input.getBody());
            final Product result = productService.addProduct(request);

            return new APIGatewayProxyResponseEvent()
                    .withBody(writer.writeValueAsString(result))
                    .withStatusCode(200);
        } catch (Exception ex) {
            return generateServerException(ex);
        }
    }

    private APIGatewayProxyResponseEvent generateServerException(Exception ex) {
        return new APIGatewayProxyResponseEvent()
                .withBody(ex.getMessage())
                .withStatusCode(500);
    }
}
