import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Store } from '@ngrx/store';
import * as quizPageActions from '../../../shared/store/quiz/actions/quiz-page.actions';
import { Quiz } from 'src/app/shared/models/quiz.model';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css'],
})
export class ModalComponent {
  form = new FormGroup({
    score: new FormControl<number>(0),
    showAnswers: new FormControl<boolean>(false),
    showFinalResults: new FormControl<boolean>(false),
    chanceNum: new FormControl<number>(0),
    remark: new FormControl<string>(''),
    durationInMinutes: new FormControl<number>(0),
  });

  constructor(
    private store: Store,
    private dialogRef: MatDialogRef<ModalComponent>
  ) {}

  addQuiz() {
    const quiz: Quiz = {
      score: this.form.value.score ?? 0,
      showAnswers: this.form.value.showAnswers ?? false,
      showFinalResults: this.form.value.showFinalResults ?? false,
      chanceNum: this.form.value.chanceNum ?? 0,
      remark: this.form.value.remark ?? '',
      durationInMinutes: this.form.value.durationInMinutes ?? 0,
      trainer_id: 1,
    };

    this.store.dispatch(quizPageActions.addQuiz({ quiz }));
    this.dialogRef.close();
  }
}
