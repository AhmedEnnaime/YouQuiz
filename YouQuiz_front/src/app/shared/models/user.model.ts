export class User {
  public firstName: string;
  public lastName: string;
  public birthDate: Date;
  public address: string;

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
