import { User } from './user.model';

export class Student extends User {
  private id?: number;
  private dateOfInscription: Date;

  constructor(
    firstName: string,
    lastName: string,
    birthDate: Date,
    address: string,
    dateOfInscription: Date,
    id?: number
  ) {
    super(firstName, lastName, birthDate, address);
    this.id = id;
    this.dateOfInscription = dateOfInscription;
  }
}
