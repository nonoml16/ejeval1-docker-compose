import { Component, OnInit, TemplateRef } from '@angular/core';
import { SubjectA } from '../../models/subject.interface';
import { PostAsignaturaDTO } from '../../models/created-asignatura.interface';
import { SubjectService } from '../../services/subject.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TeacherItem } from '../../models/teacher-list.interface';
import { TeacherService } from '../../services/teacher.service';
@Component({
  selector: 'app-page-subject-list',
  templateUrl: './page-subject-list.component.html',
  styleUrl: './page-subject-list.component.css',
})
export class PageSubjectListComponent implements OnInit {
  SubjectAList: SubjectA[] = [];
  page = 0;
  nombre: string = '';
  horas: number = 0;

  idProfeS: string = '';
  descripcion: string = '';
  color: string = '';
  nomErr: string = '';
  horasErrormin: string = '';
  idProError: string = '';
  descripError: string = '';
  colorError: string = '';
  profesorList!: TeacherItem[];

  constructor(
    private asignaturaService: SubjectService,
    private modalService: NgbModal,
    private profesoresService: TeacherService
  ) {}
  ngOnInit(): void {
    this.getProfesores();
  }
  capturar(id: any) {
    this.idProfeS = id;
  }
  open(content: TemplateRef<any>) {
    this.modalService.open(content);
  }
  getProfesores() {
    this.profesoresService.getAllTeacher().subscribe((resp) => {
      this.profesorList = resp.content;
    });
  }
  createdAsigna() {
    let newAsig: PostAsignaturaDTO = new PostAsignaturaDTO(
      this.nombre,
      this.horas,
      this.idProfeS,
      this.descripcion,
      this.color
    );
    console.log(newAsig);
    debugger;
    this.asignaturaService.createdAsignatura(newAsig).subscribe({
      next: (data) => {
        window.location.href = 'http://localhost:4200/subjects/';
      },
      error: (err) => {
        if ((err.status = 404)) {
          let badReqs = err;
          let errores = badReqs.error.body.fields_errors;
          errores.forEach((error: { field: any; message: any }) => {
            switch (error.field) {
              case 'nombre':
                this.nomErr = error.message;
                break;
              case 'horas':
                this.horasErrormin = error.message;
                break;
              case 'idProfesor':
                this.idProError = error.message;
                break;
              case 'descripcion':
                this.descripError = error.message;
                break;
              case 'color':
                this.colorError = error.message;
                break;
            }
          });
        }
      },
    });
  }
}
