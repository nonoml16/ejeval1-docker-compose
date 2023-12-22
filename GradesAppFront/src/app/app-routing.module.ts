import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './ui/login/login.component';
import { RegisterComponent } from './ui/register/register.component';
import { PageSubjectListComponent } from './ui/page-subject-list/page-subject-list.component';
import { StudentDetailsComponent } from './ui/student-details/student-details.component';
import { SubjectDetailsComponent } from './ui/subject-details/subject-details.component';
import { InstrumentListComponent } from './ui/instrument-list/instrument-list.component';
import { StudentMarksComponent } from './ui/student-marks/student-marks.component';
import { InstrumentDetailsComponent } from './ui/instrument-details/instrument-details.component';
import { PageNotFoundComponent } from './ui/page-not-found/page-not-found.component';
import { AdminSectionComponent } from './sections/admin-section/admin-section.component';
import { PageTeacherListComponent } from './ui/page-teacher-list/page-teacher-list.component';
import { PageAsignaturaTeacherComponent } from './ui/page-asignatura-teacher/page-asignatura-teacher.component';
import { PageStudentListComponent } from './ui/page-student-list/page-student-list.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: '', component: AdminSectionComponent, children: [
      { path: 'subjects', component: PageSubjectListComponent },
      { path: 'student/subject/:id', component: StudentMarksComponent },
      { path: 'teachers', component: PageTeacherListComponent },
      { path: 'teacher/:id/students', component: PageStudentListComponent },
      { path: 'teacher/subject/:id', component: SubjectDetailsComponent },
      {
        path: 'student/instrument/:id',
        component: InstrumentListComponent,
      },
      { path: 'teacher/:id/subjects', component: PageAsignaturaTeacherComponent},
      { path: 'teacher/student/:id', component: StudentDetailsComponent },
      { path: 'teacher/subject/instrument/:id',component: InstrumentDetailsComponent},
      {path: 'teacher/subject/:id_asig/instrument/:id',component: InstrumentDetailsComponent},
      { path: '', redirectTo: '/login', pathMatch: 'full' },
    ]
  },
  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
