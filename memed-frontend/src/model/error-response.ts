export type ErrorResponse = {
  statusCode: number;
  timestamp: string;
  message: string;
  fieldErrors: Map<string, string>;
  globalErrors: Array<string>;
};
