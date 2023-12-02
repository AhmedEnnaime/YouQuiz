import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { QuizService } from 'src/app/services/quiz.service';
import { Quiz } from 'src/app/shared/models/quiz.model';
import { ModalComponent } from '../../modals/modal/modal.component';
import { Store } from '@ngrx/store';
import { loadQuizzes } from 'src/app/shared/store/actions/quiz.action';

@Component({
  selector: 'app-quizzes',
  templateUrl: './quizzes.component.html',
  styleUrls: ['./quizzes.component.css'],
})
export class QuizzesComponent implements OnInit {
  quizzes: Quiz[] = [];

  constructor(
    private store: Store<{ quizzes: { quizzes: Quiz[] } }>,
    public dialog: MatDialog
  ) {
    store.select('quizzes').subscribe((quizzesState: { quizzes: Quiz[] }) => {
      this.quizzes = quizzesState.quizzes;
    });
  }

  openDialog() {
    this.dialog.open(ModalComponent, {
      enterAnimationDuration: '400ms',
      exitAnimationDuration: '400ms',
      autoFocus: false,
    });
  }

  ngOnInit(): void {
    this.store.dispatch(loadQuizzes({ quizzes: this.quizzes }));
  }
}
