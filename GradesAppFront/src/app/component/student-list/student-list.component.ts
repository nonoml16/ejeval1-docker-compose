import { Component, Input } from '@angular/core';
import { AlumnoService } from '../../services/alumno.service';
import { AlumnoP } from '../../models/alumno-profesor-list.inteface';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrl: './student-list.component.css'
})
export class StudentListComponent{
  
  @Input() list: AlumnoP[] = [];
}
