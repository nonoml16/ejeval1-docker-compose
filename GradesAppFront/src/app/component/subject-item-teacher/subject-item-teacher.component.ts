import { Component, Input } from '@angular/core';
import { SubjectA } from '../../models/subject.interface';

@Component({
  selector: 'app-subject-item-teacher',
  templateUrl: './subject-item-teacher.component.html',
  styleUrl: './subject-item-teacher.component.css'
})
export class SubjectItemTeacherComponent {
  @Input() subject!: SubjectA;
}
