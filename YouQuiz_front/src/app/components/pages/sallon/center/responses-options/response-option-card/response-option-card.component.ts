import { Component, Input } from '@angular/core';
import { IValidation } from 'src/app/shared/models/IValidation';

@Component({
  selector: 'app-response-option-card',
  templateUrl: './response-option-card.component.html',
  styleUrls: ['./response-option-card.component.css'],
})
export class ResponseOptionCardComponent {
  @Input() validation?: IValidation;
}
