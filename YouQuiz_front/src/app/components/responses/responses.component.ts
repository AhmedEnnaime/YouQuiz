import { Component, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { IValidation } from 'src/app/shared/models/IValidation';

@Component({
  selector: 'app-responses',
  templateUrl: './responses.component.html',
  styleUrls: ['./responses.component.css'],
})
export class ResponsesComponent {
  @Input() validations?: Observable<IValidation[]>;

  constructor() {}
}
