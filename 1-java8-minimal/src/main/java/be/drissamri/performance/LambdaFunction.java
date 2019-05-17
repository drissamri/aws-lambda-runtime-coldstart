package be.drissamri.performance;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class LambdaFunction implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private final AwsLambdaComponent component;

    public LambdaFunction() {
        this.component = DaggerAwsLambdaComponent.builder().build();
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        final String result = this.component.pingService().pong();

        final APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setBody(result);
        response.setStatusCode(200);
        return response;
    }
}
