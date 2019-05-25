package be.drissamri.serverlessspring.lambda;

import be.drissamri.serverlessspring.model.Product;
import be.drissamri.serverlessspring.model.ProductRequest;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class AwsLambdaHandler extends SpringBootRequestHandler<ProductRequest, Product> {
}