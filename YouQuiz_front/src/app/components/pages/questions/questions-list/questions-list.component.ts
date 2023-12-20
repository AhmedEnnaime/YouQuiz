import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Store } from '@ngrx/store';
import { ITempoQuiz } from 'src/app/shared/models/ITempoQuiz';
import { IValidation } from 'src/app/shared/models/IValidation';
import * as tempoPageActions from '../../../../shared/store/tempo/actions/tempo-page.actions';
import { Observable } from 'rxjs';
import { selectTempos } from 'src/app/shared/store/tempo/tempo.selector';
import { ITempoID } from 'src/app/shared/models/ITempoID';

@Component({
  selector: 'app-questions-list',
  templateUrl: './questions-list.component.html',
  styleUrls: ['./questions-list.component.css'],
})
export class QuestionsListComponent implements OnInit {
  tempos: Observable<ITempoQuiz[]>;
  quizID?: number;
  validations?: IValidation[];
  tempoID: ITempoID = { quizID: undefined, questionID: undefined };

  constructor(private store: Store, private route: ActivatedRoute) {
    this.route.paramMap.subscribe((params) => {
      const idString = params.get('id');
      this.tempoID = {
        quizID: idString !== null ? +idString : 0,
        questionID: undefined,
      };
    });
    this.tempos = store.select(selectTempos);
  }

  ngOnInit(): void {
    this.store.dispatch(tempoPageActions.enter({ tempoID: this.tempoID }));
  }
}
