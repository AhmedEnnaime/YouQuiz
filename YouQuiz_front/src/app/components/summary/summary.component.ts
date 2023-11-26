import { Component, OnInit } from '@angular/core';
import { StudentService } from 'src/app/services/student.service';
import { TrainerService } from 'src/app/services/trainer.service';
import { Student } from 'src/app/shared/models/student.model';
import { Trainer } from 'src/app/shared/models/trainer.model';

@Component({
  selector: 'app-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.css'],
})
export class SummaryComponent implements OnInit {
  students: Student[] = [];
  trainers: Trainer[] = [];

  constructor(
    private studentService: StudentService,
    private trainerService: TrainerService
  ) {}

  ngOnInit(): void {
    this.studentService.getStudents().subscribe((students) => {
      this.students = students;
    });
    this.trainerService.getTrainers().subscribe((trainers) => {
      this.trainers = trainers;
    });
  }
}
