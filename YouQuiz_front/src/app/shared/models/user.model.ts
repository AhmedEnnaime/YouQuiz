export class User {
  protected firstName: string;
  protected lastName: string;
  protected birthDate: Date;
  protected address: string;

  constructor(
    firstName: string,
    lastName: string,
    birthDate: Date,
    address: string
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.address = address;
  }

  getName(): string {
    return `${this.firstName} ${this.lastName}`;
  }
}
