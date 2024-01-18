import {Lead} from "../model/lead";

export type AuthResponseDto = {
  token: string;
  lead: Lead;
}
