import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-question-type-button',
  templateUrl: './question-type-button.component.html',
  styleUrls: ['./question-type-button.component.css'],
})
export class QuestionTypeButtonComponent {
  @Input() options: any;
}
