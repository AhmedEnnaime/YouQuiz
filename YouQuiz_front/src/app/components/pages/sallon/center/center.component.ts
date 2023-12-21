import { Component, Input, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ITempoQuiz } from 'src/app/shared/models/ITempoQuiz';
import { IValidation } from 'src/app/shared/models/IValidation';

@Component({
  selector: 'app-center',
  templateUrl: './center.component.html',
  styleUrls: ['./center.component.css'],
})
export class CenterComponent implements OnInit {
  @Input() tempos?: Observable<ITempoQuiz[]>;
  @Input() selectedQuestion: ITempoQuiz | null = null;
  @Input() validations?: Observable<IValidation[]>;

  // emptyValidations: IValidation[] = [
  //   {
  //     question: { questionText: '', questionType: 0, totalScore: 0 },
  //     response: { response: '' },
  //     points: 0,
  //   },
  //   {
  //     question: { questionText: '', questionType: 0, totalScore: 0 },
  //     response: { response: '' },
  //     points: 0,
  //   },
  //   {
  //     question: { questionText: '', questionType: 0, totalScore: 0 },
  //     response: { response: '' },
  //     points: 0,
  //   },
  //   {
  //     question: { questionText: '', questionType: 0, totalScore: 0 },
  //     response: { response: '' },
  //     points: 0,
  //   },
  // ];

  ngOnInit(): void {
    // this.validations?.subscribe((validationsList) => {
    //   if (validationsList.length == 0) {
    //     this.validations = new Observable((observer) => {
    //       observer.next(this.emptyValidations);
    //       observer.complete();
    //     });
    //   }
    // });
  }
}
