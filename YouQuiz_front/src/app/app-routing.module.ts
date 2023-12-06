import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainLayoutComponent } from './shared/main-layout/main-layout.component';
import { DashboardComponent } from './components/pages/dashboard/dashboard.component';
import { NotFoundComponent } from './shared/not-found/not-found.component';
import { QuizzesComponent } from './components/pages/quizzes/quizzes.component';
import { QuestionsComponent } from './components/pages/questions/questions.component';
import { SallonComponent } from './components/pages/sallon/sallon.component';

const routes: Routes = [
  {
    path: '',
    component: MainLayoutComponent,
    children: [{ path: '', component: DashboardComponent }],
  },
  {
    path: 'quizzes',
    component: MainLayoutComponent,
    children: [{ path: '', component: QuizzesComponent }],
  },
  {
    path: 'questions/:id',
    component: MainLayoutComponent,
    children: [{ path: '', component: QuestionsComponent }],
  },
  {
    path: 'sallon/:id',
    component: MainLayoutComponent,
    children: [{ path: '', component: SallonComponent }],
  },
  {
    path: '**',
    pathMatch: 'full',
    component: MainLayoutComponent,
    children: [{ path: '', component: NotFoundComponent }],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
