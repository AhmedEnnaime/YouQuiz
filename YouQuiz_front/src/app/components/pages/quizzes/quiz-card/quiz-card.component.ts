import { Component, Input, OnInit } from '@angular/core';
import { Quiz } from 'src/app/shared/models/quiz.model';

@Component({
  selector: 'app-quiz-card',
  templateUrl: './quiz-card.component.html',
  styleUrls: ['./quiz-card.component.css'],
})
export class QuizCardComponent implements OnInit {
  @Input() props?: any;
  quiz?: Quiz;

  ngOnInit(): void {
    this.quiz = {
      ...this.props,
    };
  }
}
