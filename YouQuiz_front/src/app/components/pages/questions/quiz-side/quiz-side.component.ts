import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {
  faClock,
  faChalkboardTeacher,
  faClipboard,
  faPen,
} from '@fortawesome/free-solid-svg-icons';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Quiz } from 'src/app/shared/models/quiz.model';
import { selectFoundedQuiz } from 'src/app/shared/store/quiz/quiz.selector';
import * as quizPageActions from '../../../../shared/store/quiz/actions/quiz-page.actions';

@Component({
  selector: 'app-quiz-side',
  templateUrl: './quiz-side.component.html',
  styleUrls: ['./quiz-side.component.css'],
})
export class QuizSideComponent implements OnInit {
  quizID?: number;
  quiz: Observable<Quiz | null>;
  clock = faClock;
  trainerIcon = faChalkboardTeacher;
  scoreIcon = faClipboard;
  penIcon = faPen;
  constructor(
    private store: Store,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.route.paramMap.subscribe((params) => {
      const idString = params.get('id');
      this.quizID = idString !== null ? +idString : 0;
    });
    this.quiz = this.store.select(selectFoundedQuiz);
  }

  goToSallonPage() {
    this.router.navigate(['/sallon', this.quizID]);
  }

  ngOnInit() {
    this.store.dispatch(
      quizPageActions.findQuiz({ quizID: this.quizID as number })
    );
  }
}
