import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ButtonComponent } from 'src/app/shared/button/button.component';
import { GreetingsComponent } from './components/greetings/greetings.component';
import { HeaderComponent } from './shared/header/header.component';
import { IconComponent } from './shared/icon/icon.component';
import { fas } from '@fortawesome/free-solid-svg-icons';
import {
  FaIconLibrary,
  FontAwesomeModule,
} from '@fortawesome/angular-fontawesome';
import { DashboardComponent } from './components/pages/dashboard/dashboard.component';
import { MainLayoutComponent } from './shared/main-layout/main-layout.component';
import { ProfileComponent } from './components/pages/profile/profile.component';
import { SummaryComponent } from './components/summary/summary.component';
import { SummaryCardComponent } from './components/summary/summary-card/summary-card.component';
import { TodayQuizComponent } from './components/today-quiz/today-quiz.component';
import { TodayQuizCardComponent } from './components/today-quiz/today-quiz-card/today-quiz-card.component';
import { AssignedQuizzesComponent } from './components/assigned-quizzes/assigned-quizzes.component';
import { AssignedQuizCardComponent } from './components/assigned-quizzes/assigned-quiz-card/assigned-quiz-card.component';

@NgModule({
  declarations: [
    AppComponent,
    ButtonComponent,
    GreetingsComponent,
    HeaderComponent,
    IconComponent,
    DashboardComponent,
    MainLayoutComponent,
    ProfileComponent,
    SummaryComponent,
    SummaryCardComponent,
    TodayQuizComponent,
    TodayQuizCardComponent,
    AssignedQuizzesComponent,
    AssignedQuizCardComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, FontAwesomeModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {
  constructor(library: FaIconLibrary) {
    library.addIconPacks(fas);
  }
}
