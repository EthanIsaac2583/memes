import {Question} from "../model/question";
import axios, {AxiosError, AxiosResponse} from "axios";
import {ErrorResponse} from "../model/error-response";

export class QuestionRepository {

  public async nextQuestion(quizId: number): Promise<Question> {
    return axios({
      method: 'GET',
      url: `http://192.168.100.5:8080/api/v1/quizzes/${quizId}/questions/next`
    })
      .then((response: AxiosResponse<Question>) => {
        return response.data;
      })
      .catch((errorResponse: AxiosError<ErrorResponse>) => {
        throw errorResponse?.response?.data;
      });
  }
}
