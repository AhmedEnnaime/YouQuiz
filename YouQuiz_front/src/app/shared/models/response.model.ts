export class Response {
  private id?: number;
  private response: string;

  constructor(id: number, response: string) {
    this.id = id;
    this.response = response;
  }
}
