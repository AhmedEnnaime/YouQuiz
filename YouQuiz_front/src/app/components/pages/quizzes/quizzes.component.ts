import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { QuizService } from 'src/app/services/quiz.service';
import { Quiz } from 'src/app/shared/models/quiz.model';
import { ModalComponent } from '../../modals/modal/modal.component';

@Component({
  selector: 'app-quizzes',
  templateUrl: './quizzes.component.html',
  styleUrls: ['./quizzes.component.css'],
})
export class QuizzesComponent implements OnInit {
  quizzes: Quiz[] = [];

  constructor(private quizService: QuizService, public dialog: MatDialog) {}

  openDialog() {
    let dialogRef = this.dialog.open(ModalComponent, {
      enterAnimationDuration: '400ms',
      exitAnimationDuration: '400ms',
      autoFocus: false,
    });
    // this.levelState.setState(dialogRef);
  }

  ngOnInit(): void {
    this.quizService.getQuizzes().subscribe((quizzes) => {
      this.quizzes = quizzes;
    });
  }
}
