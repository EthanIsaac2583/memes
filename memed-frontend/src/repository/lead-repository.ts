import axios, {AxiosError, AxiosResponse} from "axios";
import {ApplicationLocalStorage, StorageKey} from "../util/application-local-storage";
import {Lead} from "../model/lead";
import {ErrorResponse} from "../model/error-response";

export class LeadRepository {

  private readonly baseUrl: string;

  constructor(baseUrl: string) {
    this.baseUrl = baseUrl;
  }

  public async findMe() {
    return axios({
      method: 'GET',
      baseURL: this.baseUrl,
      url: 'api/v1/private/leads/me',
      headers: {
        "Authorization": `Bearer ${ApplicationLocalStorage.getItem(StorageKey.Token)}`
      }
    })
      .then((response: AxiosResponse<Lead>) => {
        return response.data;
      })
      .catch((errorResponse: AxiosError<ErrorResponse>) => {
        throw errorResponse.response?.data;
      });
  }
}
