import {Question} from "../model/question";
import axios, {AxiosError, AxiosResponse} from "axios";
import {ErrorResponse} from "../model/error-response";
import {Answer} from "../model/answer";
import {Item} from "../model/item";
import {ApplicationHeader} from "./application-header";
import {ApplicationLocalStorage, StorageKey} from "../util/application-local-storage";

export class QuestionRepository {

  private readonly baseUrl: string;

  constructor(baseUrl: string) {
    this.baseUrl = baseUrl;
  }

  public async nextQuestion(quizId: string, number: string | null): Promise<Item<Question>> {
    return axios({
      method: 'GET',
      baseURL: this.baseUrl,
      url: `/api/v1/quizzes/${quizId}/questions/item`,
      params: {
        number
      },
      headers: {
        [ApplicationHeader.VisitId]: ApplicationLocalStorage.getItem(StorageKey.VisitId)
      }
    })
      .then((response: AxiosResponse<Item<Question>>) => {
        return response.data;
      })
      .catch((errorResponse: AxiosError<ErrorResponse>) => {
        throw errorResponse?.response?.data;
      });
  }

  public async provideAnswer(quizId: string, questionId: number, answer: Answer): Promise<void> {
    return axios({
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        [ApplicationHeader.VisitId]: ApplicationLocalStorage.getItem(StorageKey.VisitId)
      },
      baseURL: this.baseUrl,
      url: `/api/v1/quizzes/${quizId}/questions/${questionId}`,
      data: { answer }
    })
      .then(() => {})
      .catch((errorResponse: AxiosError<ErrorResponse>) => {
        throw errorResponse?.response?.data;
      });
  }
}
