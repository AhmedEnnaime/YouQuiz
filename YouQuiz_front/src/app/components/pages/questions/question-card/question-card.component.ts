import { Component, Input } from '@angular/core';
import { ITempoQuiz } from 'src/app/shared/models/ITempoQuiz';

@Component({
  selector: 'app-question-card',
  templateUrl: './question-card.component.html',
  styleUrls: ['./question-card.component.css'],
})
export class QuestionCardComponent {
  @Input() props?: any;
  tempo?: ITempoQuiz;
}
