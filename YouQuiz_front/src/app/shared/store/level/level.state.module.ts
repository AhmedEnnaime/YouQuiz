import { NgModule } from '@angular/core';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { LevelReducer } from './level.reducer';
import { LevelEffect } from './level.effect';

export const LEVEL_FEATURE_KEY = 'LEVEL';

@NgModule({
  imports: [
    StoreModule.forFeature(LEVEL_FEATURE_KEY, LevelReducer),
    EffectsModule.forFeature([LevelEffect]),
  ],
})
export class LevelStateModule {}
