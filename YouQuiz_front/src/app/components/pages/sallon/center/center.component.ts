import { Component, Input } from '@angular/core';
import { ITempoQuiz } from 'src/app/shared/models/ITempoQuiz';
import { IValidation } from 'src/app/shared/models/IValidation';

@Component({
  selector: 'app-center',
  templateUrl: './center.component.html',
  styleUrls: ['./center.component.css'],
})
export class CenterComponent {
  @Input() tempos: ITempoQuiz[] = [];
  @Input() selectedQuestion: ITempoQuiz | null = null;
  @Input() validations: IValidation[] = [];
}
