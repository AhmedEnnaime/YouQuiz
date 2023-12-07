import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-quiz-question-card',
  templateUrl: './quiz-question-card.component.html',
  styleUrls: ['./quiz-question-card.component.css'],
})
export class QuizQuestionCardComponent {
  @Input() questionNum?: number;
  @Input() isSelected: boolean = false;
}
