import axios, {AxiosError, AxiosResponse} from "axios";
import {ErrorResponse} from "../model/error-response";
import {Visit} from "../model/visit";

export class VisitRepository {

  private readonly baseUrl: string;

  constructor(baseUrl: string) {
    this.baseUrl = baseUrl;
  }

  public async findById(id: string) {
    return axios({
      method: 'POST',
      baseURL: this.baseUrl,
      url: `/api/v1/visits/${id}`
    })
      .then((response: AxiosResponse<Visit>) => {
        return response.data;
      })
      .catch((errorResponse: AxiosError<ErrorResponse>) => {
        throw errorResponse?.response?.data;
      });
  }

  public async create() {
    return axios({
      method: 'POST',
      baseURL: this.baseUrl,
      url: '/api/v1/visits',
      data: {}
    })
      .then((response: AxiosResponse<Visit>) => {
        return response.data;
      })
      .catch((error: AxiosError<ErrorResponse>) => {
        throw error?.response?.data;
      });
  }
}
