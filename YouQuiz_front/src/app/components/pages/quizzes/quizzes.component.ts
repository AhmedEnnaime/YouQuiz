import { Component, OnInit } from '@angular/core';
import { QuizService } from 'src/app/services/quiz.service';
import { Quiz } from 'src/app/shared/models/quiz.model';

@Component({
  selector: 'app-quizzes',
  templateUrl: './quizzes.component.html',
  styleUrls: ['./quizzes.component.css'],
})
export class QuizzesComponent implements OnInit {
  quizzes: Quiz[] = [];
  show = false;

  constructor(private quizService: QuizService) {}

  openModal() {
    this.show = true;
  }

  ngOnInit(): void {
    this.quizService.getQuizzes().subscribe((quizzes) => {
      this.quizzes = quizzes;
    });
  }
}
