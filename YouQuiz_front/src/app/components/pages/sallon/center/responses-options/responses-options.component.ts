import { Component, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { IValidation } from 'src/app/shared/models/IValidation';

@Component({
  selector: 'app-responses-options',
  templateUrl: './responses-options.component.html',
  styleUrls: ['./responses-options.component.css'],
})
export class ResponsesOptionsComponent {
  @Input() validations?: Observable<IValidation[]>;
}
