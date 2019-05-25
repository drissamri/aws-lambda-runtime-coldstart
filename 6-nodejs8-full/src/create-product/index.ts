import middy from '@middy/core';
import httpEventNormalizer from '@middy/http-event-normalizer';
import { productHandler } from './product.handler';

const handler = middy(productHandler)
  .use(httpEventNormalizer());

module.exports = { handler };
