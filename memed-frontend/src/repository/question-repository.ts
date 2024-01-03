import {Question} from "../model/question";
import axios, {AxiosError, AxiosResponse} from "axios";
import {ErrorResponse} from "../model/error-response";
import {Answer} from "../model/answer";
import {Item} from "../model/item";

export class QuestionRepository {

  private readonly baseUrl: string;

  constructor(baseUrl: string) {
    this.baseUrl = baseUrl;
  }

  public async nextQuestion(quizId: number, number?: number): Promise<Item<Question>> {
    return axios({
      method: 'GET',
      baseURL: this.baseUrl,
      url: `/api/v1/quizzes/${quizId}/questions/item`,
      params: {
        number
      }
    })
      .then((response: AxiosResponse<Item<Question>>) => {
        return response.data;
      })
      .catch((errorResponse: AxiosError<ErrorResponse>) => {
        throw errorResponse?.response?.data;
      });
  }

  public async provideAnswer(questionId: number , answer: Answer): Promise<void> {
    return axios({
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      baseURL: this.baseUrl,
      url: `/api/v1/questions/${questionId}`,
      data: { answer }
    })
      .then(() => {})
      .catch((errorResponse: AxiosError<ErrorResponse>) => {
        throw errorResponse?.response?.data;
      });
  }
}
