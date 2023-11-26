export class Student {
  private id?: number;
  private firstName: string;
  private lastName: string;
  private birthDate: Date;
  private address: string;
  private dateOfInscription: Date;

  constructor(
    id: number,
    firstName: string,
    lastName: string,
    birthDate: Date,
    address: string,
    dateOfInscription: Date
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.address = address;
    this.dateOfInscription = dateOfInscription;
  }

  getName(): string {
    return `${this.firstName} ${this.lastName}`;
  }
}
