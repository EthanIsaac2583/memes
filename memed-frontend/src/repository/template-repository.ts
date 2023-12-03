import axios, {AxiosError, AxiosResponse} from "axios";
import {IPageable} from "../model/general";
import {TQuizTemplate} from "../model/quiz-template";
import {ErrorResponse} from "../model/error-response";

export class TemplateRepository {

  private readonly baseUrl: string;

  constructor(baseUrl: string) {
    this.baseUrl = baseUrl;
  }

  public async findAll() {
    return axios({
      method: 'GET',
      baseURL: this.baseUrl,
      url: '/api/v1/templates'
    })
      .then((response: AxiosResponse<IPageable<TQuizTemplate>>) => {
        return response.data;
      })
      .catch((error: AxiosError<ErrorResponse>) => {
        throw error?.response?.data;
      });
  }
}
