import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Store } from '@ngrx/store';
import { ITempoQuiz } from 'src/app/shared/models/ITempoQuiz';
import { loadTempos } from 'src/app/shared/store/actions/tempo.action';

@Component({
  selector: 'app-questions-list',
  templateUrl: './questions-list.component.html',
  styleUrls: ['./questions-list.component.css'],
})
export class QuestionsListComponent implements OnInit {
  tempos: ITempoQuiz[] = [];
  quizID?: number;

  constructor(
    private store: Store<{ tempos: { tempos: ITempoQuiz[] } }>,
    private route: ActivatedRoute
  ) {
    store
      .select('tempos')
      .subscribe((temposState: { tempos: ITempoQuiz[] }) => {
        this.tempos = temposState.tempos;
      });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      const idString = params.get('id');
      this.quizID = idString !== null ? +idString : 0;
    });
    this.store.dispatch(
      loadTempos({ tempos: this.tempos, quizID: this.quizID ?? 0 })
    );
  }
}
