import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Observable } from 'rxjs';
import { ITempoQuiz } from 'src/app/shared/models/ITempoQuiz';
import { IValidation } from 'src/app/shared/models/IValidation';

@Component({
  selector: 'app-center',
  templateUrl: './center.component.html',
  styleUrls: ['./center.component.css'],
})
export class CenterComponent implements OnInit {
  @Input() tempos?: Observable<ITempoQuiz[]>;
  @Input() selectedQuestion?: ITempoQuiz;
  @Input() validations?: Observable<IValidation[]>;
  @Output() selectedQuestionTextChange = new EventEmitter<string | null>();
  questionText: string | null | undefined =
    this.selectedQuestion?.question?.questionText;

  onSelectedQuestionChange(): void {
    const textValue = this.questionText ?? null;
    this.selectedQuestionTextChange.emit(textValue);
  }

  ngOnInit(): void {
    this.onSelectedQuestionChange();
  }
}
