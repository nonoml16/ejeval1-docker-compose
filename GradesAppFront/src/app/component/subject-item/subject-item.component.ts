import { Component, Input } from '@angular/core';
import { SubjectA } from '../../models/subject.interface';

@Component({
  selector: 'app-subject-item',
  templateUrl: './subject-item.component.html',
  styleUrl: './subject-item.component.css',
})
export class SubjectItemComponent {
  @Input() subject!: SubjectA;
}
