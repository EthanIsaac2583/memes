import {Quiz} from "../model/quiz";
import axios, {AxiosError, AxiosResponse} from "axios";
import {ErrorResponse} from "../model/error-response";
import {ApplicationLocalStorage, StorageKey} from "../util/application-local-storage";
import {ApplicationHeader} from "./application-header";

export class QuizRepository {

  private readonly baseUrl: string;

  constructor(baseUrl: string) {
    this.baseUrl = baseUrl;
  }

  public async findById(quizId: number) {
    return axios({
      method: 'GET',
      baseURL: this.baseUrl,
      url: `/api/v1/quizzes/${quizId}`,
      headers: {
        [ApplicationHeader.VisitId]: ApplicationLocalStorage.getItem(StorageKey.VisitId)
      }
    })
      .then((response: AxiosResponse<Quiz>) => {
        return response.data;
      })
      .catch((error: AxiosError<ErrorResponse>) => {
        throw error?.response?.data;
      });
  }

  public async requestByTemplateId(templateId: number) {
    return axios({
      method: 'GET',
      baseURL: this.baseUrl,
      url: `/api/v1/templates/${templateId}/quizzes/request`,
      headers: {
        [ApplicationHeader.VisitId]: ApplicationLocalStorage.getItem(StorageKey.VisitId)
      }
    })
      .then((response: AxiosResponse<Quiz>) => {
        return response.data;
      })
      .catch((exception: AxiosError<ErrorResponse>) => {
        throw exception?.response?.data;
      });
  }

  public async finalizeById(id: number): Promise<Quiz> {
    return axios({
      method: 'PUT',
      baseURL: this.baseUrl,
      url: `api/v1/quizzes/${id}/finalize`,
      headers: {
        [ApplicationHeader.VisitId]: ApplicationLocalStorage.getItem(StorageKey.VisitId)
      }
    })
      .then((response: AxiosResponse<Quiz>) => {
        return response.data;
      })
      .catch((exception: AxiosError<ErrorResponse>) => {
        throw exception?.response?.data;
      });
  }
}
