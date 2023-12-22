import { Component, Input, TemplateRef } from '@angular/core';
import { Teacher } from '../../models/teacher-list-response';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TeacherService } from '../../services/teacher.service';

@Component({
  selector: 'app-teacher-item',
  templateUrl: './teacher-item.component.html',
  styleUrl: './teacher-item.component.css'
})
export class TeacherItemComponent {
  nombre: string = '';
  apellidos: string = '';
  titulo: string = '';
  nombreError: string = '';
  apellidosError: string = '';
  emailError: string = '';
  tituloError: string = '';
  @Input() teacher!: Teacher;

  constructor(private modalService: NgbModal, private teacherService: TeacherService) { }

  popoverClicked(event: MouseEvent) {
    event.stopPropagation();
  }

  getChar() {
    return this.teacher.nombre.charAt(0);
  }

  open(content: TemplateRef<any>) {
    this.modalService.open(content);
    this.nombre = this.teacher.nombre;
    this.apellidos = this.teacher.apellidos;
    if(this.teacher.titulacion != null)
    this.titulo = this.teacher.titulacion;
  }

  editTeacher(){
    this.nombreError = '';
    this.apellidosError = '';
    this.tituloError = '';

    this.teacherService.editTeacher(this.teacher.id, this.titulo, this.nombre, this.apellidos).subscribe({
      next: data => {
        this.modalService.dismissAll();
      },

      error: err => {
        let errors = err.error.body.fields_errors;
        errors.forEach((erro: { field: any; message: any; }) => {
          switch (erro.field) {
            case "nombre":
              this.nombreError = erro.message;
              break;

            case "apellidos":
              this.apellidosError = erro.message;
              break;

            case "titulacion":
              this.tituloError = erro.message;
              break;
          }

        });
      }

    })
  }

  delete(){
    this.teacherService.deleteTeacher(this.teacher.id).subscribe(ans => {
      window.location.reload();
    });
  }
}
