import { NgModule } from '@angular/core';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { TempoReducer } from './tempo.reducer';
import { TempoEffect } from './tempo.effect';

export const TEMPO_FEATURE_KEY = 'TEMPO';

@NgModule({
  imports: [
    StoreModule.forFeature(TEMPO_FEATURE_KEY, TempoReducer),
    EffectsModule.forFeature([TempoEffect]),
  ],
})
export class TempoStateModule {}
