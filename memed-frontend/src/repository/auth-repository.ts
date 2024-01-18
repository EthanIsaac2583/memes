import {AuthDto} from "../dto/auth-dto";
import {AuthResponseDto} from "../dto/auth-response-dto";
import axios, {AxiosError, AxiosResponse} from "axios";
import {ErrorResponse} from "../model/error-response";

export class AuthRepository {

  private readonly baseUrl: string;

  constructor(baseUrl: string) {
    this.baseUrl = baseUrl;
  }

  public async register(data: AuthDto): Promise<AuthResponseDto> {
    return axios({
      method: 'POST',
      baseURL: this.baseUrl,
      url: '/api/v1/auth/register',
      data
    })
      .then((response: AxiosResponse<AuthResponseDto>) => {
        return response.data;
      })
      .catch((error: AxiosError<ErrorResponse>) => {
        throw error?.response?.data;
      });
  }

  public async login(data: AuthDto): Promise<AuthResponseDto> {
    return axios({
      method: 'POST',
      baseURL: this.baseUrl,
      url: '/api/v1/auth/login',
      data
    })
      .then((response: AxiosResponse<AuthResponseDto>) => {
        return response.data;
      })
      .catch((error: AxiosError<ErrorResponse>) => {
        throw error?.response?.data;
      });
  }
}
