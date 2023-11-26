export class User {
  private firstName: string;
  private lastName: string;
  private birthDate: Date;
  private address: string;

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
