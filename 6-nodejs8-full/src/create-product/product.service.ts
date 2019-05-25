import * as uuid from 'uuid';
import { Product } from './products.interfaces';
import { ProductError } from '../shared/errors';

export class ProductService {

  // TODO: Can I import DocumentClient type without importing extra code?
  //       import { DocumentClient } from 'aws-sdk/clients/dynamodb';
  constructor(private readonly _dynamoDbClient,
              private readonly _tableName: string) {
    if (!this._tableName) {
      throw new Error('DynamoDB table name must be set.');
    }
  }

  public createProduct(productName: string): Promise<Product> {
    return new Promise((
      resolve: (result: Product) => void,
      reject: (reason: ProductError) => void): void => {

      const params = {
        TableName: this._tableName,
        Item: {
          id: uuid.v1(),
          name: productName
        }
      };

      // TODO: A better/prettier way to do this?
      this._dynamoDbClient.put(params).promise()
        .then(() => {
          const product: Product = {
            id: params.Item.id,
            name: params.Item.name
          };
          resolve(product);
        })
        .catch((error) => {
          reject(new ProductError('INTERNAL_SERVER_ERROR', 'Internal server error!'));
          return;
        });
    });
  }
}
