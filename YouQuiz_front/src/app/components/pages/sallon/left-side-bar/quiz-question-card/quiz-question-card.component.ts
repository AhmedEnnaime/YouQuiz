import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ITempoQuiz } from 'src/app/shared/models/ITempoQuiz';

@Component({
  selector: 'app-quiz-question-card',
  templateUrl: './quiz-question-card.component.html',
  styleUrls: ['./quiz-question-card.component.css'],
})
export class QuizQuestionCardComponent {
  @Input() questionNum?: number;
  @Input() temposLength: number = 0;
  @Input() isSelected: boolean = false;
  @Input() tempo?: ITempoQuiz;
  @Output() deleteEmptyQuestion = new EventEmitter<void>();
  @Output() detachedQuestion = new EventEmitter<{
    questionID: number | undefined | null;
    index: number | undefined;
  }>();

  sendDetachedQuestion(
    questionID: number | undefined | null,
    index: number | undefined
  ): void {
    if ((index as number) < this.temposLength) {
      this.detachedQuestion.emit({ questionID, index });
    } else {
      this.deleteEmptyQuestion.emit();
    }
  }
}
