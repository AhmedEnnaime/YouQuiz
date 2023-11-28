import { Component, OnInit } from '@angular/core';
import { AssignQuizService } from 'src/app/services/assign-quiz.service';
import { AssignQuiz } from 'src/app/shared/models/assign-quiz.model';

@Component({
  selector: 'app-upcoming-quizzes',
  templateUrl: './upcoming-quizzes.component.html',
  styleUrls: ['./upcoming-quizzes.component.css'],
})
export class UpcomingQuizzesComponent implements OnInit {
  upcomingQuizzes: AssignQuiz[] = [];
  numOfUpcomingQuizzes: number = 0;

  constructor(private assignQuizService: AssignQuizService) {}
  ngOnInit(): void {
    this.assignQuizService.getAssignments().subscribe((upcomingQuizzes) => {
      this.upcomingQuizzes = upcomingQuizzes;
      this.numOfUpcomingQuizzes = upcomingQuizzes.length;
      console.log(upcomingQuizzes);
    });
  }
}
