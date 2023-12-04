import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { SubjectService } from 'src/app/services/subject.service';
import * as SubjectActions from '../actions/subject.action';
import { Subject } from '../../models/subject.model';
import { exhaustMap, map } from 'rxjs';

@Injectable()
export class SubjectEffects {
  constructor(
    private actions$: Actions,
    private subjectService: SubjectService
  ) {}

  loadSubjects$ = createEffect(() =>
    this.actions$.pipe(
      ofType(SubjectActions.loadSubjects),
      exhaustMap(() =>
        this.subjectService
          .getSubjects()
          .pipe(
            map((subjects: Subject[]) =>
              SubjectActions.loadSubjects({ subjects })
            )
          )
      )
    )
  );
}
