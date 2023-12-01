import {Quiz} from "../model/quiz";
import axios, {AxiosError, AxiosResponse} from "axios";
import {ErrorResponse} from "../model/error-response";

export class QuizRepository {

  private readonly baseUrl: string;

  constructor(baseUrl: string) {
    this.baseUrl = baseUrl;
  }

  public async finalizeById(id: number): Promise<Quiz> {
    return axios({
      method: 'PATCH',
      baseURL: this.baseUrl,
      url: `api/v1/quizzes/${id}/finalize`
    })
      .then((response: AxiosResponse<Quiz>) => {
        return response.data;
      })
      .catch((exception: AxiosError<ErrorResponse>) => {
        throw exception?.response?.data;
      });
  }
}
