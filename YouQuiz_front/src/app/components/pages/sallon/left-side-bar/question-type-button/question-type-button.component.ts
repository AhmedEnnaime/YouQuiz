import { Component, Input, OnInit } from '@angular/core';
import { QuestionType } from 'src/app/core/enums/QuestionType';

@Component({
  selector: 'app-question-type-button',
  templateUrl: './question-type-button.component.html',
  styleUrls: ['./question-type-button.component.css'],
})
export class QuestionTypeButtonComponent implements OnInit {
  @Input() options: any;

  ngOnInit(): void {
    console.log(this.options);
  }
}
