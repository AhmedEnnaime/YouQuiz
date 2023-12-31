import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Quiz } from 'src/app/shared/models/quiz.model';

@Component({
  selector: 'app-quiz-card',
  templateUrl: './quiz-card.component.html',
  styleUrls: ['./quiz-card.component.css'],
})
export class QuizCardComponent {
  @Input() quiz?: Quiz;

  constructor(private router: Router) {}

  goToQuestionsPage() {
    this.router.navigate(['/questions', this.quiz?.id]);
  }
}
