import { Component, Input, OnInit } from '@angular/core';
import { IValidation } from 'src/app/shared/models/IValidation';
import { faCheck, faXmark } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-response-card',
  templateUrl: './response-card.component.html',
  styleUrls: ['./response-card.component.css'],
})
export class ResponseCardComponent {
  @Input()
  validation?: IValidation;
  correctIcon = faCheck;
  falseIcon = faXmark;
}
