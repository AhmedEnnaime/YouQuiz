import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { ITempoQuiz } from 'src/app/shared/models/ITempoQuiz';
import * as tempoPageActions from '../../../../shared/store/tempo/actions/tempo-page.actions';
import { ITempoID } from 'src/app/shared/models/ITempoID';

@Component({
  selector: 'app-left-side-bar',
  templateUrl: './left-side-bar.component.html',
  styleUrls: ['./left-side-bar.component.css'],
})
export class LeftSideBarComponent implements OnInit {
  @Input() tempos?: Observable<ITempoQuiz[]>;
  selectedQuestionIndex: number = 0;
  @Output() selectedQuestion = new EventEmitter<number>();
  temposList: ITempoQuiz[] = [];
  temposLength: number = 0;
  tempoID?: ITempoID;

  constructor(private store: Store, private route: ActivatedRoute) {}

  selectQuestion(index: number): void {
    this.selectedQuestionIndex = index;
    this.selectedQuestion.emit(index);
  }

  addEmptyTempo(): void {
    this.temposList?.push({ time: null });
  }

  getTemposLength(): number {
    this.tempos?.subscribe(
      (temposList) => (this.temposLength = temposList.length)
    );
    return this.temposLength;
  }

  deleteEmptyQuestion(index: number): void {
    this.temposList = this.temposList.filter((_, i) => i !== index);
    if (this.temposList.length != 0 && this.selectedQuestionIndex != index) {
      this.selectQuestion(this.selectedQuestionIndex);
    } else if (this.temposList.length == 0) {
      this.selectQuestion(this.getTemposLength() - 1);
    } else {
      this.selectQuestion(index);
    }
  }

  detachQuestionFromQuiz(data: {
    questionID: number | undefined | null;
    index: number | undefined;
  }): void {
    this.route.paramMap.subscribe((params) => {
      const idString = params.get('id');
      this.tempoID = {
        quizID: idString !== null ? +idString : 0,
        questionID: data.questionID as number,
      };
    });
    if (this.selectedQuestionIndex == data.index) {
      this.selectQuestion(data.index);
    }

    this.store.dispatch(
      tempoPageActions.deleteTempo({ tempoID: this.tempoID })
    );
  }

  ngOnInit(): void {
    this.tempos?.subscribe((tempos) => {
      this.temposLength = tempos?.length || 0;
    });
  }
}
