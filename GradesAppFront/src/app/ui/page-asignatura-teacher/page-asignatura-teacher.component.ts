import { Component } from '@angular/core';
import { SubjectA } from '../../models/subject.interface';

@Component({
  selector: 'app-page-asignatura-teacher',
  templateUrl: './page-asignatura-teacher.component.html',
  styleUrl: './page-asignatura-teacher.component.css'
})
export class PageAsignaturaTeacherComponent {
  SubjectAList: SubjectA[] = [];
}
