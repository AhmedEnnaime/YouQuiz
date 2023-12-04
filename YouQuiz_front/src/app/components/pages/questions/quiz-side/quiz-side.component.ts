import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {
  faClock,
  faChalkboardTeacher,
  faClipboard,
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
  constructor(
    private route: ActivatedRoute,
    private quizService: QuizService
  ) {}

  ngOnInit() {
    this.route.paramMap.subscribe((params) => {
      const idString = params.get('id');
      this.quizID = idString !== null ? +idString : 0;
      console.log('Quiz ID:', this.quizID);
    });

    this.quizService.getQuizByID(this.quizID ?? 0).subscribe((quiz) => {
      this.quiz = quiz;
    });
  }
}
