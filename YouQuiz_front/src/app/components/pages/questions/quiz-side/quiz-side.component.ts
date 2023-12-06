import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {
  faClock,
  faChalkboardTeacher,
  faClipboard,
  faPen,
} from '@fortawesome/free-solid-svg-icons';
import { QuizService } from 'src/app/services/quiz.service';
import { Quiz } from 'src/app/shared/models/quiz.model';

@Component({
  selector: 'app-quiz-side',
  templateUrl: './quiz-side.component.html',
  styleUrls: ['./quiz-side.component.css'],
})
export class QuizSideComponent implements OnInit {
  quizID?: number;
  quiz?: Quiz;
  clock = faClock;
  trainerIcon = faChalkboardTeacher;
  scoreIcon = faClipboard;
  penIcon = faPen;
  constructor(
    private route: ActivatedRoute,
    private quizService: QuizService,
    private router: Router
  ) {}

  goToSallonPage() {
    this.router.navigate(['/sallon', this.quizID]);
  }

  ngOnInit() {
    this.route.paramMap.subscribe((params) => {
      const idString = params.get('id');
      this.quizID = idString !== null ? +idString : 0;
    });

    this.quizService.getQuizByID(this.quizID ?? 0).subscribe((quiz) => {
      this.quiz = quiz;
    });
  }
}
