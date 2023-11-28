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
      const futureAssignments = upcomingQuizzes.filter(
        (quiz) => new Date(quiz.debutDate) > new Date()
      );

      const uniqueAssignmentsMap = new Map<string, AssignQuiz>();
      futureAssignments.forEach((assignment) => {
        const key =
          assignment.quiz.id +
          '-' +
          assignment.debutDate +
          '-' +
          assignment.endDate;

        if (
          !uniqueAssignmentsMap.has(key) ||
          new Date(assignment.debutDate) <
            new Date(uniqueAssignmentsMap.get(key)!.debutDate)
        ) {
          uniqueAssignmentsMap.set(key, assignment);
        }
      });
      this.upcomingQuizzes = Array.from(uniqueAssignmentsMap.values());

      this.numOfUpcomingQuizzes = this.upcomingQuizzes.length;
    });
  }
}
