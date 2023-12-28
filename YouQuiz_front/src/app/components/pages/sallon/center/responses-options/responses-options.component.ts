import { Component, Input, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { IValidation } from 'src/app/shared/models/IValidation';

@Component({
  selector: 'app-responses-options',
  templateUrl: './responses-options.component.html',
  styleUrls: ['./responses-options.component.css'],
})
export class ResponsesOptionsComponent implements OnInit {
  @Input() validations?: Observable<IValidation[]>;

  ngOnInit(): void {
    // this.validations?.subscribe((validationsList) => {
    //   console.log(validationsList);
    //   if (!validationsList || validationsList.length === 0) {
    //     console.log('HERE');
    //     const defaultValidations = [
    //       { points: 0 },
    //       { points: 0 },
    //       { points: 0 },
    //       { points: 0 },
    //     ];
    //     this.validations = of(defaultValidations);
    //   }
    // });
  }
}
