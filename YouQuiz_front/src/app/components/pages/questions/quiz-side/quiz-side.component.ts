import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-quiz-side',
  templateUrl: './quiz-side.component.html',
  styleUrls: ['./quiz-side.component.css'],
})
export class QuizSideComponent implements OnInit {
  quizID?: number;

  constructor(private route: ActivatedRoute) {}

  ngOnInit() {
    this.route.paramMap.subscribe((params) => {
      const idString = params.get('id');
      this.quizID = idString !== null ? +idString : 0;
      console.log('Quiz ID:', this.quizID);
    });
  }
}
