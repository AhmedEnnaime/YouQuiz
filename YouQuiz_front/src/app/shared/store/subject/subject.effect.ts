import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { SubjectService } from 'src/app/services/subject.service';
import * as subjectPageActions from './actions/subject-page.actions';
import * as subjectApiActions from './actions/subject.api.actions';
import { concatMap, exhaustMap, map } from 'rxjs';

@Injectable()
export class SubjectEffect {
  constructor(
    private actions$: Actions,
    private subjectService: SubjectService
  ) {}

  loadSubject$ = createEffect(() =>
    this.actions$.pipe(
      ofType(subjectPageActions.enter),
      exhaustMap(() =>
        this.subjectService
          .getSubjects()
          .pipe(
            map((subjects) =>
              subjectApiActions.subjectsLoadedSuccessfully({ subjects })
            )
          )
      )
    )
  );

  createSubject$ = createEffect(() =>
    this.actions$.pipe(
      ofType(subjectPageActions.addSubject),
      concatMap((action) =>
        this.subjectService
          .addSubject(action.subject)
          .pipe(
            map((addedSubject) =>
              subjectApiActions.subjectAddedSuccessfully({ addedSubject })
            )
          )
      )
    )
  );
}
