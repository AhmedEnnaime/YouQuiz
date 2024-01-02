import {
  Component,
  Input,
  OnChanges,
  OnInit,
  SimpleChanges,
} from '@angular/core';
import { Observable, of } from 'rxjs';
import { IValidation } from 'src/app/shared/models/IValidation';

@Component({
  selector: 'app-responses-options',
  templateUrl: './responses-options.component.html',
  styleUrls: ['./responses-options.component.css'],
})
export class ResponsesOptionsComponent implements OnInit {
  @Input() validations?: Observable<IValidation[]>;
  validationsList?: IValidation[];

  private handleValidations(): void {
    this.validations?.subscribe((validationsList) => {
      validationsList.length == 0
        ? (this.validationsList = [
            { points: 0 },
            { points: 0 },
            { points: 0 },
            { points: 0 },
          ])
        : (this.validationsList = validationsList);
    });
  }

  ngOnInit(): void {
    this.handleValidations();
  }
}
