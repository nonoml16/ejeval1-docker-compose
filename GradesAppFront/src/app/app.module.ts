import { NgModule } from '@angular/core';
import {
  BrowserModule,
} from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule, provideHttpClient, withFetch, withInterceptors } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './ui/login/login.component';
import { RegisterComponent } from './ui/register/register.component';
import { SubjectDetailsComponent } from './ui/subject-details/subject-details.component';
import { SubjectItemComponent } from './component/subject-item/subject-item.component';
import { SubjectListComponent } from './component/subject-list/subject-list.component';
import { StudentDetailsComponent } from './ui/student-details/student-details.component';
import { StudentMarksComponent } from './ui/student-marks/student-marks.component';
import { InstrumentDetailsComponent } from './ui/instrument-details/instrument-details.component';
import { InstrumentListComponent } from './ui/instrument-list/instrument-list.component';
import { PageNotFoundComponent } from './ui/page-not-found/page-not-found.component';
import { NavbarComponent } from './component/navbar/navbar.component';
import { TeacherSubjectComponent } from './component/teacher-subject/teacher-subject.component';
import { TeacherInstrumentItemComponent } from './component/teacher-instrument-item/teacher-instrument-item.component';
import {
  FontAwesomeModule,
  FaIconLibrary,
} from '@fortawesome/angular-fontawesome';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { TeacherInstrumentListComponent } from './component/teacher-instrument-list/teacher-instrument-list.component';
import { FormsModule } from '@angular/forms';

import { StudentItemComponent } from './component/student-item/student-item.component';
import { StudentListComponent } from './component/student-list/student-list.component';
import { PageStudentListComponent } from './ui/page-student-list/page-student-list.component';
import { TeacherReferentListComponent } from './component/teacher-referent-list/teacher-referent-list.component';
import { PageSubjectListComponent } from './ui/page-subject-list/page-subject-list.component';
import { AdminSectionComponent } from './sections/admin-section/admin-section.component';
import { TeacherItemComponent } from './component/teacher-item/teacher-item.component';
import { TeacherListComponent } from './component/teacher-list/teacher-list.component';
import { PageTeacherListComponent } from './ui/page-teacher-list/page-teacher-list.component';
import { TeacherCalificacionTableComponent } from './component/teacher-calificacion-table/teacher-calificacion-table.component';
import { LoggerInterceptor } from './logger.interceptor';
import { RemoveWrapperInterceptor } from './RemoveWrapperInterceptor';
import { TeacherReferenteItemComponent } from './component/teacher-referente-item/teacher-referente-item.component';
import { SubjectItemTeacherComponent } from './component/subject-item-teacher/subject-item-teacher.component';
import { SubjectListTeacherComponent } from './component/subject-list-teacher/subject-list-teacher.component';
import { PageAsignaturaTeacherComponent } from './ui/page-asignatura-teacher/page-asignatura-teacher.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    SubjectDetailsComponent,
    SubjectItemComponent,
    StudentListComponent,
    StudentDetailsComponent,
    StudentMarksComponent,
    InstrumentDetailsComponent,
    InstrumentListComponent,
    PageNotFoundComponent,
    NavbarComponent,
    TeacherSubjectComponent,
    TeacherInstrumentItemComponent,
    TeacherInstrumentListComponent,
    StudentItemComponent,
    PageStudentListComponent,
    TeacherReferentListComponent,
    PageSubjectListComponent,
    SubjectListComponent,
    AdminSectionComponent,
    TeacherItemComponent,
    TeacherListComponent,
    PageTeacherListComponent,
    TeacherCalificacionTableComponent,
    TeacherReferenteItemComponent,
    SubjectItemTeacherComponent,
    SubjectListTeacherComponent,
    PageAsignaturaTeacherComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: LoggerInterceptor, multi: true }, provideHttpClient(withFetch()), {
    provide: HTTP_INTERCEPTORS,
    useClass: RemoveWrapperInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent],
})
export class AppModule {
  constructor(library: FaIconLibrary) {
    library.addIconPacks(fas);
  }
}
