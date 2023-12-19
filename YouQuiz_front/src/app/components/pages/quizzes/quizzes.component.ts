import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Quiz } from 'src/app/shared/models/quiz.model';
import { ModalComponent } from '../../modals/modal/modal.component';
import { Store } from '@ngrx/store';
import * as quizPageActions from '../../../shared/store/quiz/actions/quiz-page.actions';
import { Observable } from 'rxjs';
import { selectQuizzes } from 'src/app/shared/store/quiz/quiz.selector';

@Component({
  selector: 'app-quizzes',
  templateUrl: './quizzes.component.html',
  styleUrls: ['./quizzes.component.css'],
})
export class QuizzesComponent implements OnInit {
  quizzes: Observable<Quiz[]>;

  constructor(private store: Store, public dialog: MatDialog) {
    this.quizzes = store.select(selectQuizzes);
  }

  openDialog() {
    this.dialog.open(ModalComponent, {
      enterAnimationDuration: '400ms',
      exitAnimationDuration: '400ms',
      autoFocus: false,
    });
  }

  ngOnInit(): void {
    this.store.dispatch(quizPageActions.enter());
  }
}
