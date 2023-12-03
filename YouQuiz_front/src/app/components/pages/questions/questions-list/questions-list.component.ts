import { Component } from '@angular/core';
import { Question } from 'src/app/shared/models/question.model';

@Component({
  selector: 'app-questions-list',
  templateUrl: './questions-list.component.html',
  styleUrls: ['./questions-list.component.css'],
})
export class QuestionsListComponent {
  questions: Question[] = [];
}
