export class StandardMapper {

  public static parseToNumber(stringedNumber?: string | null) {
    if (stringedNumber) {
      return parseInt(stringedNumber, 10);
    }

    throw new Error("Unprocessable number");
  }
}
