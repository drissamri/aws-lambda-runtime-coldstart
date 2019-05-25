import { APIGatewayEvent } from 'aws-lambda';
import { ProductService } from './product.service';
import { DynamoDB } from 'aws-sdk'
import * as https from "https";

const dynamoDbClient = new DynamoDB.DocumentClient();

const db: ProductService = new ProductService(dynamoDbClient, process.env.DYNAMODB_TABLE);

export async function productHandler(event: APIGatewayEvent) {
  try {
    console.time("db-insert");
    const product = await db.createProduct(getProductName(event));
    console.timeEnd("db-insert");
    return {
      statusCode: 200,
      body: JSON.stringify(product)
    };
  } catch (error) {
    return generateErrorBody(error);
  }
}

function getProductName(event: APIGatewayEvent) {
  if (event.body !== null && event.body !== undefined) {
    const body = JSON.parse(event.body);
    if (!body.name) {
      throw new Error('Missing productName');
    }
    return body.name;
  }
}

function generateErrorBody(error) {
  return {
    statusCode: 500,
    body: JSON.stringify(
      {  error: error.message })
  };
}

function createSslAgent() {
  return new https.Agent({
    keepAlive: true,          // Disabled by default
    maxSockets: 50,           // AWS defaults
    rejectUnauthorized: true  // AWS defaults
  })
}