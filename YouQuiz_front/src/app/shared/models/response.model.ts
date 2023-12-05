export class Response {
  public id?: number;
  public response: string;

  constructor(id: number, response: string) {
    this.id = id;
    this.response = response;
  }
}
