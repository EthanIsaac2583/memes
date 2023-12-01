import {Question} from "../model/question";
import axios, {AxiosError, AxiosResponse} from "axios";
import {ErrorResponse} from "../model/error-response";
import {Answer} from "../model/answer";

export class QuestionRepository {

  private readonly baseUrl: string;

  constructor(baseUrl: string) {
    this.baseUrl = baseUrl;
  }

  public async nextQuestion(quizId: number): Promise<Question> {
    return axios({
      method: 'GET',
      url: `${this.baseUrl}/api/v1/quizzes/${quizId}/questions/next`
    })
      .then((response: AxiosResponse<Question>) => {
        return response.data;
      })
      .catch((errorResponse: AxiosError<ErrorResponse>) => {
        throw errorResponse?.response?.data;
      });
  }

  public async provideAnswer(questionId: number , answer: Answer): Promise<void> {
    return axios({
      method: 'PATCH',
      url: `${this.baseUrl}/api/v1/questions/${questionId}`,
      data: { answer }
    })
      .then(() => {})
      .catch((errorResponse: AxiosError<ErrorResponse>) => {
        throw errorResponse?.response?.data;
      });
  }
}
