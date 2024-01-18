export type ErrorResponse = {
  statusCode: number;
  timestamp: string;
  message: string;
  fieldErrors: Record<string, string>;
  globalErrors: Array<string>;
};
