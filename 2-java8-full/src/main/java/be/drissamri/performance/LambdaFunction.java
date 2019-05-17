package be.drissamri.performance;

import be.drissamri.performance.model.Product;
import be.drissamri.performance.model.ProductRequest;
import be.drissamri.performance.services.ProductService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LambdaFunction implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    static final Logger log = LogManager.getLogger(LambdaFunction.class);

    private final Gson gson;
    private final ProductService productService;

    public LambdaFunction() {
        final AwsLambdaComponent component = DaggerAwsLambdaComponent.builder().build();
        this.gson = component.gson();
        this.productService = component.productService();
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        final APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();

        try {
            long startTime = System.currentTimeMillis();
            final ProductRequest request = this.gson.fromJson(input.getBody(), ProductRequest.class);
            long endTime = System.currentTimeMillis();
            log.info("Serialization: " + (endTime - startTime) + " milliseconds");

            final Product result = this.productService.addProduct(request);

            long startTime2 = System.currentTimeMillis();
            response.setBody(this.gson.toJson(result));
            long endTime2 = System.currentTimeMillis();
            log.info("Deserialization: " + (endTime2 - startTime2) + " milliseconds");
            response.setStatusCode(200);
        } catch (final Exception e) {
            response.setStatusCode(500);
        }

        return response;
    }
}
