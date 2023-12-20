import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Observable } from 'rxjs';
import { ITempoQuiz } from 'src/app/shared/models/ITempoQuiz';

@Component({
  selector: 'app-left-side-bar',
  templateUrl: './left-side-bar.component.html',
  styleUrls: ['./left-side-bar.component.css'],
})
export class LeftSideBarComponent {
  @Input() tempos?: Observable<ITempoQuiz[]>;
  selectedQuestionIndex: number = 0;
  @Output() selectedQuestion = new EventEmitter<number>();

  selectQuestion(index: number): void {
    this.selectedQuestionIndex = index;
    this.selectedQuestion.emit(index);
  }
}
