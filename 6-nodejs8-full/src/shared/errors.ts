export abstract class ErrorResult extends Error {
  public constructor(public code: string, public description: string) {
    super(description);
  }
}

export class ProductError extends ErrorResult {}
